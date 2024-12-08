package com.ecommerce.repository;

import com.ecommerce.DatabaseConnection;
import com.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert a new product
    public int saveProduct(Product product) {
        String sql = "INSERT INTO Products (productName, brand, price, quantity, listingDate, imageUrl) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, product.getProductName(), product.getBrand(), product.getPrice(), 
                                   product.getQuantity(), product.getListingDate(), product.getImageUrl());
    }

    // Find a product by ID
    public Optional<Product> findById(Integer productID) {
        String sql = "SELECT * FROM Products WHERE productID = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new Object[]{productID}, new BeanPropertyRowMapper<>(Product.class));
            return Optional.ofNullable(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Find all products
    public List<Product> findAll() {
    	Connection conn = DatabaseConnection.getConnection();
		List<Product> products = new ArrayList<>();
		
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Products");
			while (rs.next()) {
				Product curr = new Product();
				curr.setProductID(rs.getInt("productID"));
				curr.setProductName(rs.getString("productName"));
				curr.setBrand(rs.getString("brand"));
				curr.setPrice(rs.getDouble("price"));
				curr.setQuantity(rs.getInt("quantity"));
				curr.setListingDate(rs.getDate("listingDate"));
				curr.setImageUrl(rs.getString("image_url"));
				curr.setRating(rs.getDouble("rating"));
				
				products.add(curr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return products;
//        String sql = "SELECT * FROM Products";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    // Update a product
    public int updateProduct(Product product) {
        String sql = "UPDATE Products SET productName = ?, brand = ?, price = ?, quantity = ?, listingDate = ?, imageUrl = ? WHERE productID = ?";
        return jdbcTemplate.update(sql, product.getProductName(), product.getBrand(), product.getPrice(), 
                                   product.getQuantity(), product.getListingDate(), product.getImageUrl(), product.getProductID());
    }

    // Delete a product by ID
    public int deleteById(Integer productID) {
        String sql = "DELETE FROM Products WHERE productID = ?";
        return jdbcTemplate.update(sql, productID);
    }
}
