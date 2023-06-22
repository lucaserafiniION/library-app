package com.example.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Rating;  
  
@Service  
public class RatingService {  
  
    @Autowired  
    private RatingRepository ratingRepository;  
  
    public Rating saveOrUpdateRating(Long userId, Long bookId, Integer ratingValue) {  
        Optional<Rating> existingRating = ratingRepository.findByUserIdAndBookId(userId, bookId);  
  
        if (existingRating.isPresent()) {  
            Rating rating = existingRating.get();  
            rating.setRating(ratingValue);  
            return ratingRepository.save(rating);  
        } else {  
            Rating newRating = new Rating();  
            newRating.setUserId(userId);  
            newRating.setBookId(bookId);  
            newRating.setRating(ratingValue);  
            return ratingRepository.save(newRating);  
        }  
    }  
  
    public Optional<Rating> getRatingByUserIdAndBookId(Long userId, Long bookId) {  
        return ratingRepository.findByUserIdAndBookId(userId, bookId);  
    }  
    
    public List<Rating> getAllRatingsByBookId(Long bookId) {  
        return ratingRepository.findByBookId(bookId);  
    }  
    
    public Double getAverageRatingByBookId(Long bookId) {  
        return ratingRepository.findAverageRatingByBookId(bookId);  
    }
}  
