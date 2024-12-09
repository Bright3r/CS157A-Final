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
import com.ecommerce.model.Product;
import com.ecommerce.model.Review;
import com.ecommerce.model.User;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;


@Repository
public class ReviewRepository {

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
    public List<Review> findByProductId(Integer productID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	List<Review> reviews = new ArrayList<>();
    	
    	// Create a prepared statement to query for reviews by product id
        String query = "SELECT * FROM Reviews WHERE productID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		// Set productID field of prepared statement query
    		pstmt.setInt(1, productID);
    		
    		// Execute Query
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Create a Review object for each row of ResultSet
    		while (rs.next()) {
    			Review review = buildReview(rs);
    			reviews.add(review);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	// Return the reviews matching the productID
    	return reviews;
    }

    // Insert a new review
    public int saveReview(Review review) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
        
    	String sql = "INSERT INTO Reviews (userID, productID, rating, reviewComment, datePosted) VALUES (?, ?, ?, ?, ?)";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, review.getUser().getUserID());
    		pstmt.setInt(2, review.getProduct().getProductID());
    		pstmt.setDouble(3, review.getRating());
    		pstmt.setString(4, review.getReviewComment());
    		pstmt.setDate(5, review.getDatePosted());
    		
    		// Execute Query
    		return pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return 0;
    }
    
    // Update a review
    public int updateReview(Review review) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
        String sql = "UPDATE Reviews SET userID = ?, productID = ?, rating = ?, "
        		+ "reviewComment = ?, datePosted = ? WHERE reviewID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, review.getUser().getUserID());
    		pstmt.setInt(2, review.getProduct().getProductID());
    		pstmt.setDouble(3, review.getRating());
    		pstmt.setString(4, review.getReviewComment());
    		pstmt.setDate(5, review.getDatePosted());
    		pstmt.setInt(6, review.getReviewID());
    		
    		// Execute Query
    		return pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return 0;
    }

    // Delete a review by ID
    public int deleteReview(Integer reviewID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();

        String sql = "DELETE FROM Reviews WHERE reviewID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, reviewID);
    		
    		// Execute Query
    		return pstmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}

    	return 0;
    }
}
