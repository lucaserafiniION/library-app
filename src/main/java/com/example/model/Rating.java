package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity  
@Table(name = "ratings")  
public class Rating {  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  

    @ManyToOne  
    @JoinColumn(name = "user_id")
    private User user;  
   
    @ManyToOne  
    @JoinColumn(name = "book_id") 
    private Book book;  
  
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
  
    public User getUser() {  
        return user;  
    }  
  
    public void setUser(User user) {  
        this.user = user;  
    }  
  
    public Book getBook() {  
        return book;  
    }  
  
    public void setBook(Book book) {  
        this.book = book;  
    }  
  
    public Integer getRating() {  
        return rating;  
    }  
  
    public void setRating(Integer rating) {  
        this.rating = rating;  
    }  
}  
