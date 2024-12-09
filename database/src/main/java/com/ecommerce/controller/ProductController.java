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
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    // Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id).orElse(null);  // Return null if not found
    }

    // Add new product
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        productService.updateProduct(product);  // Call service to add product
        return product;  // Return the product after insertion
    }

    // Update existing product
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        product.setProductID(id);  // Set the ID before updating the product
        productService.updateProduct(product);  // Call service to update product
        return product;  // Return updated product
    }

    // Delete product by ID
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);  // Call service to delete product
    }
    
    // Search for products by name
    @GetMapping("/products/search")
    public List<Product> searchProducts(@RequestParam String productName) {
    	return productService.searchProducts(productName);
    }
    
    // Filter products by category
    @GetMapping("/products/filter")
    public List<Product> filterProductsByCategory(@RequestParam String category) {
    	return productService.filterProductsByCategory(category);
    }
    
    // Get products sorted by descending price
    @GetMapping("/products/sort/price")
    public List<Product> getProductsSortedByPrice() {
    	return productService.getProductsSortedByPrice();
    }
    
    // Get products sorted by descending rating
    @GetMapping("/products/sort/rating")
    public List<Product> getProductsSortedByRating() {
    	return productService.getProductsSortedByRating();
    }
}
