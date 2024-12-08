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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ecommerce.DatabaseConnection;
import com.ecommerce.model.Address;
import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.User;
import com.ecommerce.service.AddressService;
@Repository
public class  UserRepository {

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    AddressService addressService;
    
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
    
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
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
    
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    public Optional<User> findByUserName(String userName) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper(), userName);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    public User save(User user) {
    	String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?) RETURNING userid";
        Integer generatedId = jdbcTemplate.queryForObject(sql, Integer.class, user.getUserName(), user.getEmail(), user.getPassword());
       
        user.setUserID(generatedId);
      
        return user;
        
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserID(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhoneNumber(rs.getString("phone_number"));
            return user;
        };
    }

    // Method to delete user by ID
    public void deleteById(Integer userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, userId);
    }

    public User authenticateUser(LoginRequest loginRequest) {
        String sql = "SELECT * FROM Users WHERE userName = ? AND password = ?";
        try {
            // Log the incoming parameters
            System.out.println("Authenticating user: " + loginRequest.getUsername() + " with password: " + loginRequest.getPassword());

            return jdbcTemplate.queryForObject(sql, (ResultSet rs, int rowNum) -> {
                User user = new User();
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setUserID(rs.getInt("userID"));
                user.setEmail(rs.getString("email"));
                return user;
            }, loginRequest.getUsername(), loginRequest.getPassword());
        } catch (Exception e) {
            // Log the exception
            System.out.println("Error during authentication: " + e.getMessage());
            return null;
        }
    }

}
