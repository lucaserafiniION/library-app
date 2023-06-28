package com.example.db;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.CustomUserDetails;
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
        User user = userRepository.findUserByEmail(email);
        
        if (user != null) {
            return new CustomUserDetails(Arrays.asList(new SimpleGrantedAuthority(user.getRole())),
                    user.getPassword(),
                    user.getEmail(),
                    user.isMfaEnabled(),
                    user.getSecret()
                    );
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
