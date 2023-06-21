package com.example.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.db.BookService;
import com.example.model.Book;  
  
@Controller  
@RequestMapping("/books")  
public class BookController {  
    @Autowired  
    private BookService bookService;  
  
    @GetMapping  
    public List<Book> getAllBooks() {  
        return bookService.getAllBooks();  
    }  
  
    @GetMapping("/{id}")  
    public Book getBookById(@PathVariable Long id) {  
        return bookService.getBookById(id);  
    }  
  
    @PostMapping  
    public Book addBook(@RequestBody Book book) {  
        return bookService.saveOrUpdateBook(book);  
    }
  
    @DeleteMapping("/{id}")  
    public void deleteBook(@PathVariable Long id) {  
        bookService.deleteBook(id);  
    }  
  
    @GetMapping  
    public String getAllBooks(Model model) {  
        model.addAttribute("books", bookService.getAllBooks());  
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
}  
