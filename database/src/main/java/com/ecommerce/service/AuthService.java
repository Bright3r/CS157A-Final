package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.model.User;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUserName(username);
        
        if (user.isPresent()) {
            User foundUser = user.get();
            System.out.println("Found user: " + foundUser.getUserName() + ", password: " + foundUser.getPassword());
            
            if (foundUser.getPassword().equals(password)) {
                return true;
            } else {
                System.out.println("Password mismatch for user: " + username);
            }
        } else {
            System.out.println("User not found for username: " + username);
        }
        return false;
    }

}
