package com.ecommerce.service;

import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Optional<User> findByUserName(String userName) {
    	Optional<User> user = userRepository.findByUserName(userName);
        System.out.println("Query result: " + user);
        return user;
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
    public User updateUserDetails(Integer userID, User updatedUser) {
    	
        User existingUser = userRepository.findById(userID)
                                          .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        
        return userRepository.save(existingUser);
    }

    public User loginUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUserName(username); 
        return optionalUser.filter(user -> user.getPassword().equals(password))
                           .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }

    public boolean authenticate(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUserName(username);
        optionalUser.ifPresent(user -> System.out.println("Found user: " + user.getUserName() + ", Password: " + user.getPassword()));
        return optionalUser.map(user -> user.getPassword().equals(password))
                           .orElse(false);
    }

}
