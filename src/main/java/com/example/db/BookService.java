package com.example.db;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.model.Book;  
  
@Service  
public class BookService {  
    private static final int MIN_AVG_RATING = 4;
	@Autowired  
    private BookRepository bookRepository;  
  
    public List<Book> getAllBooks() {
        return bookRepository.findAll();  
    }
    
    public List<Book> getPopularBooks() {
    	return bookRepository.findPopularBooks();
    }
  
    public List<String> searchAutocomplete(String term) {  
        List<String> suggestions = new ArrayList<>();  
        List<Book> books = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(term, term);  
  
        for (Book book : books) {  
            suggestions.add(book.getTitle());  
            suggestions.add(book.getAuthor());  
        }  
  
        return suggestions;  
    } 
    
    public List<Book> findSimilarBooks(Book book){ 
        String genre = book.getGenre();  
        String author = book.getAuthor();  
    
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Book> similarBooksPage = bookRepository.findSimilarBooksByGenreAndAuthorAndMinAvgRating(genre, author, book.getId(), MIN_AVG_RATING, pageRequest);  
  
        return similarBooksPage.getContent(); 
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
