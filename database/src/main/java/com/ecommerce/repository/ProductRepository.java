package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommerce.DatabaseConnection;
import com.ecommerce.model.Product;

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
    
    // Builds a product for the current row of the ResultSet
    // Throws an SQLException if the ResultSet does not contain expected Product data
    private Product buildProduct(ResultSet rs) throws SQLException {
		Product curr = new Product();
		
		// Set fields of Product to match database product
		curr.setProductID(rs.getInt("productID"));
		curr.setProductName(rs.getString("productName"));
		curr.setBrand(rs.getString("brand"));
		curr.setPrice(rs.getDouble("price"));
		curr.setQuantity(rs.getInt("quantity"));
		curr.setListingDate(rs.getDate("listingDate"));
		curr.setImageUrl(rs.getString("image_url"));
		curr.setRating(rs.getDouble("rating"));
		
		return curr;
    }

    // Find a product by ID
    public Optional<Product> findById(Integer productID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	Product product = null;
    	
    	// Create a prepared statement to query for product by id
    	String query = "SELECT * FROM Products WHERE productID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		// Set productID field of prepared statement query
    		pstmt.setInt(1, productID);
    		
    		// Execute Query
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Only one row since productID is primary key
    		// So build product from the first row of ResultSet
    		rs.next();
    		product = buildProduct(rs);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	// Return the Product if productID was in database,
    	// Otherwise return null
    	return Optional.ofNullable(product);
    }

    // Find all products in the database
    public List<Product> findAll() {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		List<Product> products = new ArrayList<>();
		
    	try {
    		// Query for all products in database
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Products");
			while (rs.next()) {
				// Create a Product object for each row in the ResultSet
				Product curr = buildProduct(rs);
				products.add(curr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	// Return a list of every product
    	return products;
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
