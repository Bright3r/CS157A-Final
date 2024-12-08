package com.ecommerce;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    // Database credentials
    private static final String URL = "jdbc:postgresql://localhost:5432/ecommerce";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    // Method to get a database connection
    public static Connection getConnection() {
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
    public static void main(String[] args) {
        // Try to establish a connection using try-with-resources
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection.");
            e.printStackTrace();
        }
    }
}
