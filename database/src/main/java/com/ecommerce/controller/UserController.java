package com.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Address;
import com.ecommerce.model.ApiResponse;
import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.service.AddressService;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AddressService addressService;

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
    	return userService.getAllUsers();
    }
    
    // Get a user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
    
    // Get a user by username
    @GetMapping("/username")
    public ResponseEntity<User> getUserByUserName(@RequestParam String userName) {
        Optional<User> user = userService.getUserByUserName(userName);
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
        	// Check if username is taken
        	User foundUser = userService.getUserByUserName(registerRequest.getUserName()).orElse(null);
        	if (foundUser != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Username is taken", false));
        	}
        	
        	// Construct a new user to add to database
            User user = new User();
            user.setUserName(registerRequest.getUserName());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword()	);
            
            // Add address to database
            Optional<Address> userAddr = addressService.addAddress(registerRequest.getAddress());
            if (userAddr.isEmpty()) {
            	// Failed to create address
            	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to add address to database", false));
            }
            
        	// Set user address to new database entry
            user.setAddress(userAddr.get());

            // Add user to database
            userService.addUser(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User registered successfully", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(new ApiResponse("An error occurred: " + e.getMessage(), false));
        }
    }

    // Update user details
    @PutMapping("/{userID}")
    public int updateUser(@PathVariable Integer userID, @RequestBody User updatedUser) {
        return userService.updateUser(updatedUser);
    }

    // Log in user by password
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
    	try {
    		// Try to login user
            User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
            
            // Return user object if successful
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
        	System.out.println(e.getMessage());
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
