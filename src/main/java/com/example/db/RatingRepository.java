package com.example.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Book;
import com.example.model.Rating;
import com.example.model.User;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	Optional<Rating> findByUserIdAndBookId(Long userId, Long bookId);

	List<Rating> findByBookId(Long bookId);
	
	Rating findByBookAndUser(Book book, User user);
}
