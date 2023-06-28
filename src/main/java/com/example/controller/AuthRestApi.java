package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.UserService;
import com.example.model.User;

@RestController
public class AuthRestApi {
    @Autowired  
    private UserService userService;

    @GetMapping("/login/mfa")
    @ResponseBody
    public String postLoginPage(@RequestParam Map<String, String> params, Model model){
        String username = params.get("username");
        String password = params.get("password");
        String token = params.get("mfaToken");
        if(username != null && password != null){
            User user = userService.findUserByEmail(username);
            if(user!=null && user.isMfaEnabled()){
                if(token.equals("")){
                    return "token-required";
                }else{
                    return "token-not-required";
                }
            }else{
                return "token-not-required";
            }
        }

        return "invalid-arguments";
    }

}