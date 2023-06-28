package com.example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.LibraryApplication;
import com.example.model.User;

@Service
public class UserService {
    @Autowired  
    private UserRepository userRepository;
    @Autowired
    private DefaultMFATokenManager mfaTokenManager;
  
    public User saveUser(User user) {
    	user.setPassword(LibraryApplication.passwordEncoder().encode(user.getPassword()));
        user.setSecret(mfaTokenManager.generateSecretKey());
        return userRepository.save(user);
    }
    public MfaTokenData mfaSetup(String email) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            // we will ignore in case account is not verified or account does not exists
            throw new Exception("unable to find account or account is not active");
        }
        return new MfaTokenData(mfaTokenManager.getQRCode(user.getSecret()), user.getSecret());
    }
    
    public User findUserByEmail(String email) {
    	return userRepository.findUserByEmail(email);
    }
}
