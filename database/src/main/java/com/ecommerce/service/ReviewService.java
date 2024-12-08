package com.ecommerce.service;

import com.ecommerce.model.Review;
import com.ecommerce.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Get all reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Get review by ID
    public Review getReviewById(Integer reviewID) {
        return reviewRepository.findById(reviewID);
    }

    // Get reviews by product ID
    public List<Review> getReviewsByProductId(Integer productId) {
        return reviewRepository.findByProductId(productId);
    }

    // Add a new review
    public void addReview(Review review) {
        reviewRepository.saveReview(review);
    }

    // Update a review
    public void updateReview(Review review) {
        reviewRepository.updateReview(review);
    }

    // Delete a review
    public void deleteReview(Integer reviewID) {
        reviewRepository.deleteReview(reviewID);
    }
}
