package com.example.db;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Book;  

@Repository  
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT b.* FROM library.book b WHERE CONCAT(b.author, b.genre, b.title) LIKE %:field%", nativeQuery = true)
    List<Book> findBookByField(@Param("field") String field);
}  