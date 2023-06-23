package com.example.db;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;  
  
@Service  
public class BookService {  
    @Autowired  
    private BookRepository bookRepository;  
  
    public List<Book> getAllBooks() {
        return bookRepository.findAll();  
    }

    public List<String> getAllGenre() {
        return bookRepository.findDistinctGenre();
    }

    public List<Book> getBooksByField(String field) {
        return bookRepository.findBookByField(field);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findBookByGenre(genre);
    }
  
    public Book getBookById(Long id) {  
        return bookRepository.findById(id).orElse(null);  
    }  
  
    public Book saveOrUpdateBook(Book book) {  
        return bookRepository.save(book);  
    }  
  
    public void deleteBook(Long id) {  
        bookRepository.deleteById(id);  
    }

	public BookRepository getBookRepository() {
		return bookRepository;
	}

	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}  
}  
