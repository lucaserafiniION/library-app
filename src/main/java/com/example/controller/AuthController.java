package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.db.UserService;
import com.example.model.User;

@Controller
public class AuthController {
    @Autowired  
    private UserService userService;
    
    @PostMapping(path = "/register/save",  consumes = "application/x-www-form-urlencoded")
    public String addUser(User user) {  
        userService.saveUser(user);
        return "/login";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
