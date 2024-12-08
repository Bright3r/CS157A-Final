package com.ecommerce.repository;

import com.ecommerce.model.LoginRequest;
import com.ecommerce.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
@Repository
public class  UserRepository {

    private final JdbcTemplate jdbcTemplate;
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper());
    }
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    public Optional<User> findById(Integer userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper(), userId);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
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
