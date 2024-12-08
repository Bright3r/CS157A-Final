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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.DatabaseConnection;
import com.ecommerce.model.Product;
import com.ecommerce.model.Review;
import com.ecommerce.model.User;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;


@Repository
public class ReviewRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;
    
    private Review buildReview(ResultSet rs) throws SQLException {
		Review curr = new Review();
		
		// Set fields of Product to match database product
	    curr.setReviewID(rs.getInt("reviewID"));
		curr.setRating(rs.getInt("rating"));
		curr.setReviewComment(rs.getString("reviewComment"));
		curr.setDatePosted(rs.getDate("datePosted"));
		
		// Query for review's user
		int userID = rs.getInt("userID");
		User user = userService.getUserById(userID).orElse(null);
		curr.setUser(user);
		
		// Query for review's product
		int productID = rs.getInt("productID");
		Product product = productService.getProductById(productID).orElse(null);
		curr.setProduct(product);
		
		return curr;
    }

    // Insert a new review
    public int saveReview(Review review) {
        String sql = "INSERT INTO Reviews (userID, productID, rating, reviewComment, datePosted) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, review.getUser(), review.getProduct(), review.getRating(), 
                                   review.getReviewComment(), review.getDatePosted());
    }

    // Find review by ID
    public Optional<Review> findById(Integer reviewID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	Review review = null;
    	
    	// Create a prepared statement to query for review by id
    	String query = "SELECT * FROM Reviews WHERE reviewID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		// Set reviewID field of prepared statement query
    		pstmt.setInt(1, reviewID);
    		
    		// Execute Query
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Only one row since reviewID is primary key
    		// So build review from the first row of ResultSet
    		rs.next();
    		review = buildReview(rs);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	// Return the Review if reviewID was in database,
    	// Otherwise return null
    	return Optional.ofNullable(review);
    }

    // Find reviews by product ID
    public List<Review> findByProductId(Integer productId) {
        String sql = "SELECT * FROM Reviews WHERE productID = ?";
        return jdbcTemplate.query(sql, new Object[]{productId}, new BeanPropertyRowMapper<>(Review.class));
    }

    // Find all reviews
    public List<Review> findAll() {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		List<Review> reviews = new ArrayList<>();
		
    	try {
    		// Query for all reviews in database
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Reviews");
			while (rs.next()) {
				// Create a Review object for each row in the ResultSet
				Review curr = buildReview(rs);
				reviews.add(curr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	// Return a list of every product
    	return reviews;
    }

    // Update a review
    public int updateReview(Review review) {
        String sql = "UPDATE Reviews SET userID = ?, productID = ?, rating = ?, reviewComment = ?, datePosted = ? WHERE reviewID = ?";
        return jdbcTemplate.update(sql, review.getUser(), review.getProduct(), review.getRating(),
                                   review.getReviewComment(), review.getDatePosted(), review.getReviewID());
    }

    // Delete a review by ID
    public int deleteReview(Integer reviewID) {
        String sql = "DELETE FROM Reviews WHERE reviewID = ?";
        return jdbcTemplate.update(sql, reviewID);
    }
}
