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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
     
        return userRepository.save(user);
    }
    public User updateUser(Integer userID, User updatedUser) {
        
        User existingUser = userRepository.findById(userID)
                                          .orElseThrow(() -> new RuntimeException("User not found"));

        
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        
        return userRepository.save(existingUser);
    }
    public User loginUser(String email, String password) {
  
        User user = userRepository.findByEmail(email);
        
        if (user != null && user.getPassword().equals(password)) {
            return user; 
        } else {
            throw new RuntimeException("Invalid email or password"); 
        }
    }
}
