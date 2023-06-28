package com.example.db;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.token = request.getParameter("mfaToken");
    }

}
