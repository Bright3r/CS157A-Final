package com.ecommerce;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
	// Singleton connection instance
	private static Connection conn;
	
    // Database credentials
    private static final String URL = "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    // Method to establish a database connection
    private static Connection establishConnection() {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            
            // Establish and return the connection
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Print detailed SQL error information
            System.out.println("Connection failed.");
            e.printStackTrace();
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
        }
        
        return null;
    }
    
    // Method to get the singleton database connection
    public static Connection getConnection() {
    	return conn;
    }
    
    public static void initialize() {
        // Try to establish a connection
    	conn = establishConnection();
        if (conn != null) {
            System.out.println("Connection established successfully!");
        } else {
            System.out.println("Failed to establish connection.");
        }
    }
}
