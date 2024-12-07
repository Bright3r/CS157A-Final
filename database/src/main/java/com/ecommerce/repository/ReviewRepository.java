package com.ecommerce.repository;

import com.ecommerce.model.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	List<Review> findByProduct_ProductID(Integer productId);
}
