package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.db.MfaTokenData;
import com.example.db.UserService;
import com.example.model.User;

@Controller
public class AuthController {
    @Autowired  
    private UserService userService;
    
    @PostMapping(path = "/register/save",  consumes = "application/x-www-form-urlencoded")
    public String addUser(User user, Model model) {  
        userService.saveUser(user);
        try{
            MfaTokenData mfaData = userService.mfaSetup(user.getEmail());
            model.addAttribute("qrCode", mfaData.getQrCode());
            model.addAttribute("qrCodeKey", mfaData.getMfaCode());
            model.addAttribute("qrCodeSetup", true);
        }catch(Exception e){
            model.addAttribute("failure", "Unable to setup MFA. Please try again.");
            return "/register";
        }
        return "/register";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    
    @GetMapping("/login")
    public String loginPage(@RequestParam Map<String, String> params, Model model){
        return "login";
    }
}
