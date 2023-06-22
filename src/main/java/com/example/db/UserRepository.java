package com.example.db;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;  

@Repository  
public interface UserRepository extends JpaRepository<User, Long> {  
	public User findUserByEmail(String email);
}  