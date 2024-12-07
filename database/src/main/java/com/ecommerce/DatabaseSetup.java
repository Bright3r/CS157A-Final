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
    	System.out.println("Current working directory: " + System.getProperty("user.dir"));

        String url = "jdbc:postgresql://localhost:5432/ecommerce";
        String username = "admin";  
        String password = "admin";  

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Run schema.sql
        	
            runSqlFile(conn, "./schema.sql");

            // Run seed.sql
            runSqlFile(conn, "./seed.sql");
            
            System.out.println("Database setup completed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runSqlFile(Connection conn, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder sql = new StringBuilder();

            sql.append("SET search_path TO sales, public;\n");

            System.out.println("Reading SQL file: " + filePath);

            while ((line = br.readLine()) != null) {
                sql.append(line);
                sql.append("\n");
            }

            System.out.println("Executing SQL:");
            System.out.println(sql.toString());

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
