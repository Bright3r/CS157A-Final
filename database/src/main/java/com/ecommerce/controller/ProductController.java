package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id).orElse(null);  // Return null if not found
    }

    // Add new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        productService.updateProduct(product);  // Call service to add product
        return product;  // Return the product after insertion
    }

    // Update existing product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setProductID(id);  // Set the ID before updating the product
        productService.updateProduct(product);  // Call service to update product
        return product;  // Return updated product
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);  // Call service to delete product
    }
    
    // Search for products by name
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String productName) {
    	return productService.searchProducts(productName);
    }
    
    // Filter products by category
    @GetMapping("/filter")
    public List<Product> filterProductsByCategory(@RequestParam String category) {
    	return productService.filterProductsByCategory(category);
    }
    
    // Get products sorted by descending price
    @GetMapping("/sort/price")
    public List<Product> getProductsSortedByPrice() {
    	return productService.getProductsSortedByPrice();
    }
    
    // Get products sorted by descending rating
    @GetMapping("/sort/rating")
    public List<Product> getProductsSortedByRating() {
    	return productService.getProductsSortedByRating();
    }
    
    // Search, filter, and sort products in a single endpoint
    @GetMapping("/search-filter-sort")
    public List<Product> searchFilterSortProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder) {

        return productService.searchFilterSortProducts(productName, category, sortBy);
    }
}
