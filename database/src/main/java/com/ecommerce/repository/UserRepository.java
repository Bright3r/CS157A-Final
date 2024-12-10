package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommerce.DatabaseConnection;
import com.ecommerce.model.Address;
import com.ecommerce.model.User;
import com.ecommerce.service.AddressService;
@Repository
public class  UserRepository {

    @Autowired
    AddressService addressService;
    
    // Builds a frontend user representation from a database user tuple
    private User buildUser(ResultSet rs) throws SQLException {    	
        User user = new User();
        user.setUserID(rs.getInt("userID"));
        user.setUserName(rs.getString("userName"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhoneNumber(rs.getString("phoneNumber"));
        
        // Query for user's address
        int addrID = rs.getInt("addressID");
        Address userAddr = addressService.getAddressById(addrID).orElse(null);
        user.setAddress(userAddr);
        
        return user;
    }
    
    // Method to get all users
    public List<User> findAll() {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		List<User> users = new ArrayList<>();
		
    	try {
    		// Query for all users in database
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
			while (rs.next()) {
				// Create a User object for each row in the ResultSet
				User curr = buildUser(rs);
				users.add(curr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	// Return a list of every user
    	return users;
    }
    
    // Method to get a user by userID
    public Optional<User> findById(Integer userID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	User user = null;
    	
    	// Create a prepared statement to query for user by id
    	String query = "SELECT * FROM Users WHERE userID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		// Set userID field of prepared statement query
    		pstmt.setInt(1, userID);
    		
    		// Execute Query
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Only one row since userID is primary key
    		// So build user from the first row of ResultSet
    		rs.next();
    		user = buildUser(rs);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	// Return the User if userID was in database,
    	// Otherwise return null
    	return Optional.ofNullable(user);
    }
    
    // Method to get a user by userName
    public Optional<User> findByUserName(String userName) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	User user = null;
    	
    	// Create a prepared statement to query for user by id
        String query = "SELECT * FROM users WHERE username = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		// Set userName field of prepared statement query
    		pstmt.setString(1, userName);
    		
    		// Execute Query
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Only one row since userName is unique
    		// So build user from the first row of ResultSet
    		if (rs.next()) {
        		user = buildUser(rs);	
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	// Return the User if userName was in database,
    	// Otherwise return null
    	return Optional.ofNullable(user);
    }
    
    // Insert a new user into database
    public int save(User user) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
    	// Query to create new user tuple in user table
        String sql = "INSERT INTO Users (userName, addressID, email, password, phoneNumber) "
        			+ "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getUserName());
			pstmt.setInt(2, user.getAddress().getAddrID());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getPhoneNumber());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return 0;
    }

    // Update a user's info
    public int update(User user) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
    	// Query to update attributes of a user tuple
    	String sql = "UPDATE Users SET username = ?, email = ?, password = ? WHERE userID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, user.getUserName());
    		pstmt.setString(2, user.getEmail());
    		pstmt.setString(3, user.getPassword());
    		pstmt.setInt(4, user.getUserID());
    		
    		return pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return 0;
    }

    // Method to delete user by ID
    public int deleteById(Integer userID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
    	// Query to delete user from users table by id
        String sql = "DELETE FROM users WHERE id = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, userID);
    		
    		return pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return 0;
    }
}
