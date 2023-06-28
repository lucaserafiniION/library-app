package com.example.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{
    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean mfaAuthentication;
    private String secret;
    public boolean isMfaAuthentication() {
        return mfaAuthentication;
    }

    public String getSecret() {
        return secret;
    }

    public CustomUserDetails(Collection<? extends GrantedAuthority> authorities, String password, String username,
            boolean mfaAuthentication, String secret) {
        this.authorities = authorities;
        this.password = password;
        this.username = username;
        this.mfaAuthentication = mfaAuthentication;
        this.secret = secret;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
