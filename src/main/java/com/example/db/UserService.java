package com.example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.LibraryApplication;
import com.example.model.User;

@Service
public class UserService {
    @Autowired  
    private UserRepository userRepository;  
  
    public User saveUser(User user) {
    	user.setPassword(LibraryApplication.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User findUserByEmail(String email) {
    	return userRepository.findUserByEmail(email);
    }
}
