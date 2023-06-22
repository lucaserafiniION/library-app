package com.example.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	Optional<Rating> findByUserIdAndBookId(Long userId, Long bookId);

	List<Rating> findByBookId(Long bookId);

	@Query("SELECT AVG(r.rating) FROM Rating r WHERE r.bookId = ?1")
	Double findAverageRatingByBookId(Long bookId);

}
