package com.ecommerce.repository;

import com.ecommerce.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert a new review
    public int saveReview(Review review) {
        String sql = "INSERT INTO Reviews (userID, productID, rating, reviewComment, datePosted) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, review.getUserID(), review.getProductID(), review.getRating(), 
                                   review.getReviewComment(), review.getDatePosted());
    }

    // Find review by ID
    public Review findById(Integer reviewID) {
        String sql = "SELECT * FROM Reviews WHERE reviewID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{reviewID}, new BeanPropertyRowMapper<>(Review.class));
    }

    // Find reviews by product ID
    public List<Review> findByProductId(Integer productId) {
        String sql = "SELECT * FROM Reviews WHERE productID = ?";
        return jdbcTemplate.query(sql, new Object[]{productId}, new BeanPropertyRowMapper<>(Review.class));
    }

    // Find all reviews
    public List<Review> findAll() {
        String sql = "SELECT * FROM Reviews";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Review.class));
    }

    // Update a review
    public int updateReview(Review review) {
        String sql = "UPDATE Reviews SET userID = ?, productID = ?, rating = ?, reviewComment = ?, datePosted = ? WHERE reviewID = ?";
        return jdbcTemplate.update(sql, review.getUserID(), review.getProductID(), review.getRating(),
                                   review.getReviewComment(), review.getDatePosted(), review.getReviewID());
    }

    // Delete a review by ID
    public int deleteReview(Integer reviewID) {
        String sql = "DELETE FROM Reviews WHERE reviewID = ?";
        return jdbcTemplate.update(sql, reviewID);
    }
}
