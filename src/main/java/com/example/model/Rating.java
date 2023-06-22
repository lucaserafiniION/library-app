package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;  
  
@Entity  
@Table(name = "ratings")  
public class Rating {  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    @Column(name = "user_id")  
    private Long userId;  
  
    @Column(name = "book_id")  
    private Long bookId;  
  
    @Column(name = "rating")  
    private Integer rating;  
  
    public Rating() {  
    }  
  
    // Getters and setters  
  
    public Long getId() {  
        return id;  
    }  
  
    public void setId(Long id) {  
        this.id = id;  
    }  
  
    public Long getUserId() {  
        return userId;  
    }  
  
    public void setUserId(Long userId) {  
        this.userId = userId;  
    }  
  
    public Long getBookId() {  
        return bookId;  
    }  
  
    public void setBookId(Long bookId) {  
        this.bookId = bookId;  
    }  
  
    public Integer getRating() {  
        return rating;  
    }  
  
    public void setRating(Integer rating) {  
        this.rating = rating;  
    }  
}  
