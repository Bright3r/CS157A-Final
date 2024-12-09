package com.ecommerce.service;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public boolean handleForgotPassword(String email) {
        return true; 
    }

    public void sendResetPasswordEmail(String email) {
    	
    }
    
    
    public List<User> getAllUsers() {
        return userRepository.findAll(); 
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);  
    }

    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName); 
    }

    public int addUser(User user) {
        return userRepository.save(user);  
    }

    public int updateUser(User user) {
        return userRepository.update(user); 
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);  
    }

    public User loginUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUserName(username); 
        return optionalUser.filter(user -> user.getPassword().equals(password))
                           .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }

    public boolean authenticate(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUserName(username);
        optionalUser.ifPresent(user -> System.out.println("Found user: " + user.getUserName() + ", Password: " + user.getPassword()));
        return optionalUser.map(user -> user.getPassword().equals(password)).orElse(false);
    }
}
