package com.ecommerce;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
    	// Default credentials for postgresql database
        String url = "jdbc:postgresql://localhost:5432/ecommerce";
        String username = "admin";  
        String password = "admin";  

        // Use JDBC to connect to local postgresql database
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Setup the database schema
            runSqlFile(conn, "./create_schema.sql");

            // Initialize database relations with entries
            runSqlFile(conn, "./initialize_data.sql");
            
            System.out.println("Database setup completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Executes each line of an sql file
    private static void runSqlFile(Connection conn, String filePath) {
    	// Open sql file
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder sql = new StringBuilder();

            // Read lines of file into a stringbuilder
            System.out.println("Reading SQL file: " + filePath);
            while ((line = br.readLine()) != null) {
                sql.append(line);
                sql.append("\n");
            }

            System.out.println("Executing SQL:");
            System.out.println(sql.toString());

            // Execute the sql file as a single statement
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql.toString());
                System.out.println("SQL execution completed for: " + filePath);
            }
        } catch (IOException | SQLException e) {
            System.err.println("Error executing SQL file: " + filePath);
            e.printStackTrace();
        }
    }
}
