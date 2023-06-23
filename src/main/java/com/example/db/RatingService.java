package com.example.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.model.Rating;
import com.example.model.User;  
  
@Service  
public class RatingService {  
  
    @Autowired  
    private RatingRepository ratingRepository;
    @Autowired  
    private BookRepository bookRepository;  
  
    public Optional<Rating> getRatingByUserIdAndBookId(Long userId, Long bookId) {  
        return ratingRepository.findByUserIdAndBookId(userId, bookId);  
    }  
    
    public List<Rating> getAllRatingsByBookId(Long bookId) {  
        return ratingRepository.findByBookId(bookId);  
    }
    
    public void addRating(Long book_id, User user, Integer rating) {  
        Book book = bookRepository.findById(book_id).orElseThrow(); 
        
        Rating newRating = ratingRepository.findByBookAndUser(book, user);  
        
        if(newRating == null) {
	        newRating = new Rating();  
	        newRating.setBook(book);  
	        newRating.setUser(user); 
        }
        
        newRating.setRating(rating);  
      
        book.getRatings().add(newRating);  
        user.getRatings().add(newRating);  
      
        ratingRepository.save(newRating);  
    } 
}  
