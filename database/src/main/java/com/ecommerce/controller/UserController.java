package com.ecommerce.controller;

import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // Get a user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
    @GetMapping("/findByUserName")
    public ResponseEntity<User> getUserByUserName(@RequestParam String userName) {
        Optional<User> user = userService.findByUserName(userName);
        if (user.isPresent()) {
            System.out.println("User found: " + user.get());
            return ResponseEntity.ok(user.get());
        } else {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    // Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // Update user details
    @PutMapping("/{userID}")
    public User updateUser(@PathVariable Integer userID, @RequestBody User updatedUser) {
        return userService.updateUserDetails(userID, updatedUser);
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    	try {
            System.out.println("Received login request for username: " + loginRequest.getUsername());
            System.out.println("Password: " + loginRequest.getPassword());
            User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok("Login successful");
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
