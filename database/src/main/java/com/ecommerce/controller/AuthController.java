package com.ecommerce.controller;

import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.ResponseMessage;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")  
public class AuthController {

    private AuthService authService;  
    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean authenticated = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (!authenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        return ResponseEntity.ok("Login successful");
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseMessage> forgotPassword(@RequestBody String email) {
    	UserService userService = new UserService();
    	 boolean isSuccessful = userService.handleForgotPassword(email);
		 if (isSuccessful) {
			 	return ResponseEntity.ok().body(new ResponseMessage("Password reset link sent."));
		    } else {
		    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Failed to send password reset link."));
		    }
    }
}
