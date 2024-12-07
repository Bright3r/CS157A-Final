package com.ecommerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

@RestController
public class ApiController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/data")
    public String getData() {
        return "{\"message\": \"Hello from Spring Boot!\"}";
    }

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}
