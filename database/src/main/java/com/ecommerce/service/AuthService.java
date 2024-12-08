package com.ecommerce.service;

import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        User user = userRepository.authenticateUser(loginRequest);

        if (user == null) {
            return false;  
        }

        return true;  
    }
}
