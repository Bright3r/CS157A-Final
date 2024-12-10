package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
    	// Establish JDBC Database Connection
    	DatabaseConnection.initialize();
    	
    	// Launch Backend
        SpringApplication.run(EcommerceApplication.class, args);
    }
}
