package com.ecommerce.controller;

import com.ecommerce.model.ApiResponse;
import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.RegisterRequest;
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
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User registerRequest) {
        // Check if the provided data is valid
        if (registerRequest.getUserName() == null || registerRequest.getUserName().isEmpty() ||
            registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty() ||
            registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Missing required fields.", false));
        }

        try {
            User user = new User();
            user.setUserName(registerRequest.getUserName());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());

            userService.registerUser(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User registered successfully", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ApiResponse("An error occurred: " + e.getMessage(), false));
        }
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
