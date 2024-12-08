package com.ecommerce.controller;

import com.ecommerce.model.Review;
import com.ecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Get all reviews
    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Get review by ID
    @GetMapping("/reviews/{id}")
    public Review getReviewById(@PathVariable Integer id) {
        return reviewService.getReviewById(id);
    }

    // Get reviews by product ID
    @GetMapping("/reviews/product/{productId}")
    public List<Review> getReviewsByProductId(@PathVariable Integer productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    // Add new review
    @PostMapping("/reviews")
    public void addReview(@RequestBody Review review) {
        reviewService.addReview(review);
    }

    // Update a review
    @PutMapping("/reviews/{id}")
    public void updateReview(@PathVariable Integer id, @RequestBody Review review) {
        review.setReviewID(id);  // Ensure the correct ID is set before updating
        reviewService.updateReview(review);
    }

    // Delete review by ID
    @DeleteMapping("/reviews/{id}")
    public void deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
    }
}
