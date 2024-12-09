package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    // Add new product
    public void addProduct(Product product) {
        productRepository.saveProduct(product);
    }

    // Update existing product
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    // Delete product by ID
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
    
    // Search for products by name
    public List<Product> searchProducts(String search) {
    	return productRepository.getProductsByName(search);
    }
    
    // Filter products by category
    public List<Product> filterProductsByCategory(String category) {
    	return productRepository.getProductsByCategory(category);
    }
    
    // Get products sorted by price
    public List<Product> getProductsSortedByPrice() {
    	return productRepository.getProductsSortedByPrice();
    }
    
    // Get products sorted by rating
    public List<Product> getProductsSortedByRating() {
    	return productRepository.getProductsSortedByRating();
    }
}
