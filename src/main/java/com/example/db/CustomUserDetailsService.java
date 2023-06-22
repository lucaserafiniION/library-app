package com.example.db;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}  

    @Override
    public UserDetails loadUserByUsername(String email) {
        System.out.println("Email: " + email);
        
        User user = userRepository.findUserByEmail(email);

        System.out.println("Found: " + user);
        
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
