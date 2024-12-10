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
    
    // Get all users in database
    public List<User> getAllUsers() {
        return userRepository.findAll(); 
    }

    // Get a user tuple by userID
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);  
    }

    // Get a user tuple by userName
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName); 
    }

    // Create a new user tuple in database
    public int addUser(User user) {
        return userRepository.save(user);  
    }

    // Update attributes of user tuple in database
    public int updateUser(User user) {
        return userRepository.update(user); 
    }

    // Delete a user tuple from the database
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);  
    }

    // Try to login a user by password
    public User loginUser(String username, String password) {
    	// Get corresponding user from database
        Optional<User> optionalUser = userRepository.findByUserName(username); 
        
        // Return the user object if the submitted password's hash matched the stored password hash
        return optionalUser.filter(user -> UserRepository.matchPasswords(password, user.getPassword()))
                           .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }
}
