package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindById() {
        Product product = new Product(1, "Product A", 100.0, "Category 1");
        productRepository.save(product);
        
        Product foundProduct = productRepository.findById(1).orElse(null);
        assertNotNull(foundProduct);
        assertEquals("Product A", foundProduct.getProductName());
    }
}
