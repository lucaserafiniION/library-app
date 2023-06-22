package com.example.controller;
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
import com.example.model.Book;  
  
@Controller  
@RequestMapping("/books")  
public class BookController {  
    @Autowired  
    private BookService bookService;
  
    @GetMapping("/{id}")  
    public Book getBookById(@PathVariable Long id) {  
        return bookService.getBookById(id);  
    }  
  
    @PostMapping  
    public Book addBook(@RequestBody Book book) {  
        return bookService.saveOrUpdateBook(book);  
    }
  
    @GetMapping("/delete/{id}")  
    public String deleteBook(@PathVariable Long id, Model model) {  
        bookService.deleteBook(id); 
        List<Book> allBooks = bookService.getAllBooks();
		model.addAttribute("books", allBooks);  
        return "books"; 
    }  
  
    @GetMapping  
    public String getAllBooks(Model model) {
        List<Book> allBooks = bookService.getAllBooks();
		model.addAttribute("books", allBooks);  
        return "books";  
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
        model.addAttribute("books", bookService.getBooksByField(params.get("query")));
        return "books";
    }
}  
