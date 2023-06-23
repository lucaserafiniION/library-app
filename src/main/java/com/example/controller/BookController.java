package com.example.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.db.BookService;
import com.example.db.RatingService;
import com.example.db.UserService;
import com.example.model.Book;
import com.example.model.Rating;
import com.example.model.User;

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	@Autowired
	private RatingService ratingService;

	@GetMapping("/{id}")
	public String getBookById(@PathVariable Long id, Model model) {
		Book book = bookService.getBookById(id);

		model.addAttribute("sumOfRatings", book.getRatings().stream().mapToDouble(Rating::getRating).sum());
		model.addAttribute("countOfRatings", book.getRatings().size());
		model.addAttribute("book", book);
		
		return "book_detail";
	}

	@PostMapping
	public Book addBook(@RequestBody Book book) {
		return bookService.saveOrUpdateBook(book);
	}

	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id, Model model) {
		bookService.deleteBook(id);
		List<Book> allBooks = bookService.getAllBooks();
		addBooksField(model, allBooks);
		return "books";
	}

	@GetMapping
	public String getAllBooks(Model model) {
		List<Book> allBooks = bookService.getAllBooks();

		addBooksField(model, allBooks);
		return "books";
	}

	private void addBooksField(Model model, List<Book> allBooks) {
		List<Double> sumOfRatings = new ArrayList<>();
		List<Integer> countOfRatings = new ArrayList<>();
		List<String> genres = bookService.getAllGenre();
		for (Book book : allBooks) {
			double sum = 0;
			int count = 0;
			for (Rating rating : book.getRatings()) {
				sum += rating.getRating();
				count++;
			}
			sumOfRatings.add(sum);
			countOfRatings.add(count);
		}

		model.addAttribute("sumOfRatings", sumOfRatings);
		model.addAttribute("countOfRatings", countOfRatings);
		model.addAttribute("books", allBooks);
		model.addAttribute("genres", genres);
	}

	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "add_book";
	}

	@PostMapping("/add")
	public String saveBook(@ModelAttribute Book book) {
		bookService.saveOrUpdateBook(book);
		return "redirect:/books";
	}

	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable Long id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "edit_book";
	}

	@PostMapping("/edit/{id}")
	public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
		book.setId(id);
		bookService.saveOrUpdateBook(book);
		return "redirect:/books";
	}

	@GetMapping("/search")
	public String searchBook(@RequestParam Map<String, String> params, Model model) {
		List<Book> allBooks = bookService.getBooksByField(params.get("query"));

		addBooksField(model, allBooks);

		return "books";
	}

	@GetMapping("/search/genre")
	public String searchBookByGenre(@RequestParam Map<String, String> params, Model model) {
		List<Book> allBooks = bookService.getBooksByGenre(params.get("genre"));

		addBooksField(model, allBooks);

		return "books";
	}

	@PostMapping("/rate")
	public String rateBook(@RequestParam Long bookId, @RequestParam int rating, Principal principal) {
		String username = principal.getName();
		User user = userService.findUserByEmail(username);

		ratingService.addRating(bookId, user, rating);

		return "redirect:/books";
	}
}
