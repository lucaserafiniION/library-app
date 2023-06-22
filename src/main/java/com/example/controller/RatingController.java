package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.RatingService;  
  
@RestController  
@RequestMapping("/api/ratings")  
public class RatingController {  
  
    @Autowired  
    private RatingService ratingService;  
  
    @GetMapping("/books/{bookId}/average")  
    public ResponseEntity<Double> getAverageRatingByBookId(@PathVariable Long bookId) {  
        Double averageRating = ratingService.getAverageRatingByBookId(bookId);  
        if (averageRating != null) {  
            return ResponseEntity.ok(averageRating);  
        } else {  
            return ResponseEntity.notFound().build();  
        }  
    }  
}  
