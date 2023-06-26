package com.example.db;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Book;  

@Repository  
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT b.* FROM library.book b WHERE CONCAT(b.author, b.genre, b.title) LIKE %:field%", nativeQuery = true)
    List<Book> findBookByField(@Param("field") String field);

    List<Book> findBookByGenre(@Param("genre") String genre);

    @Query(value = "SELECT DISTINCT b.genre FROM library.book b", nativeQuery = true)
    List<String> findDistinctGenre();

    @Query(value = "SELECT b.* "
    		+ "FROM library.book b JOIN library.ratings r ON b.id = r.book_id "
    		+ "GROUP BY b.id "
    		+ "ORDER BY avg(r.rating) DESC, count(*) DESC "
    		+ "LIMIT 3", nativeQuery = true) 
    List<Book> findPopularBooks();

    @Query("SELECT b FROM Book b WHERE (b.genre = :genre OR b.author = :author OR (SELECT COALESCE(AVG(r.rating), 0) FROM Rating r WHERE r.book.id = b.id) >= :minAvgRating) AND b.id != :id")  
    Page<Book> findSimilarBooksByGenreAndAuthorAndMinAvgRating(@Param("genre") String genre, @Param("author") String author, @Param("id") Long id, @Param("minAvgRating") double minAvgRating, Pageable pageable);
    
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author); 
}  