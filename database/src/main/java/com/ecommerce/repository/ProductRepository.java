package com.ecommerce.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ecommerce.DatabaseConnection;
import com.ecommerce.model.Product;

@Repository
public class ProductRepository {
    
    // Method to build a frontend product representation from
	// database product entry
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
		curr.setCategory(rs.getString("category"));
		
		return curr;
    }
    
    // Find all products in the database
    public List<Product> findAll() {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		List<Product> products = new ArrayList<>();
		
		// Query for all products in database
		String query = "SELECT * FROM Products";
    	try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			
			// Create a frontend Product object for each row in the ResultSet
			while (rs.next()) {
				Product curr = buildProduct(rs);
				products.add(curr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	// Return a list of every product
    	return products;
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
    
    // Insert a new product
    public int saveProduct(Product product) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
    	// Query to insert a new product into products table
        String sql = "INSERT INTO Products (productName, brand, price, quantity, listingDate, imageUrl, category) "
        			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getBrand());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setInt(4, product.getQuantity());
			pstmt.setDate(5, product.getListingDate());
			pstmt.setString(6, product.getImageUrl());
			pstmt.setString(7, product.getCategory());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return 0;
    }

    // Update a product
    public int updateProduct(Product product) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
    	// Query to update attributes of a product in the products table
        String sql = "UPDATE Products SET productName = ?, brand = ?, price = ?, "
        		+ "quantity = ?, listingDate = ?, imageUrl = ?, category = ? WHERE productID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getBrand());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setInt(4, product.getQuantity());
			pstmt.setDate(5, product.getListingDate());
			pstmt.setString(6, product.getImageUrl());
			pstmt.setString(7, product.getCategory());
			pstmt.setInt(8, product.getProductID());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return 0;
    }

    // Delete a product by ID
    public int deleteById(Integer productID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	
    	// Query to delete a product from products table by id
        String sql = "DELETE FROM Products WHERE productID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, productID);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return 0;
    }
    
    // Search for products by productName
    public List<Product> getProductsByName(String search) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	List<Product> results = new ArrayList<>();
    	
    	// Query for any products containing the searched word (case insensitive)
        String sql = "SELECT * FROM Products WHERE UPPER(productName) LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        // Add wildcard characters to the search string for partial matching
			String productName = "%" + search + "%";
	        pstmt.setString(1, productName.toUpperCase());
			
			// Execute query
			ResultSet rs = pstmt.executeQuery();
			
			// Add each matching product to result list
			while (rs.next()) {
				Product prod = buildProduct(rs);
				results.add(prod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return results;
    }
    
    // Search for products by category
    public List<Product> getProductsByCategory(String category) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	List<Product> results = new ArrayList<>();
    	
    	// Query for any products with the same category (case insensitive)
        String sql = "SELECT * FROM Products WHERE UPPER(category) LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        // Add wildcard characters to the search string for partial matching
			String productCategory = "%" + category + "%";
	        pstmt.setString(1, productCategory.toUpperCase());
			
			// Execute query
			ResultSet rs = pstmt.executeQuery();
			
			// Add each matching product to result list
			while (rs.next()) {
				Product prod = buildProduct(rs);
				results.add(prod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return results;
    }
    
    // Sort products by price
    public List<Product> getProductsSortedByPrice() {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	List<Product> results = new ArrayList<>();
    	
    	// Query for any products in descending order
        String sql = "SELECT * FROM Products ORDER BY price DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {	
			// Execute query
			ResultSet rs = pstmt.executeQuery();
			
			// Add each product to result list
			while (rs.next()) {
				Product prod = buildProduct(rs);
				results.add(prod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return results;
    }
    
    // Sort products by price
    public List<Product> getProductsSortedByRating() {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	List<Product> results = new ArrayList<>();
    	
    	// Query for any products in descending order
        String sql = "SELECT * FROM Products ORDER BY rating DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// Execute query
			ResultSet rs = pstmt.executeQuery();
			
			// Add each product to result list
			while (rs.next()) {
				Product prod = buildProduct(rs);
				results.add(prod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return results;
    }
    
    // Search for product by name, then filter by category, then sort by some criteria
    public List<Product> searchFilterSortProducts(String productName, String category, String sortBy) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	List<Product> results = new ArrayList<>();
    	
    	// Query for all products in database
        String query = "SELECT * FROM Products";
        List<String> queryArgs = new ArrayList<>();
        
        // only query for products with matching name
        if (productName != null) {
        	query += " WHERE UPPER(productName) LIKE ?";
        	queryArgs.add("%" + productName.toUpperCase() + "%");
        }
        
        // also check that product matches category
        if (category != null) {
        	if (productName != null) {
        		query += " AND";
        	}
        	else {
        		query += " WHERE";
        	}
        	
        	query += " UPPER(category) LIKE ?";
        	queryArgs.add("%" + category.toUpperCase() + "%");
        }
        
        // Assemble query with arguments
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        	// Add query arguments to PrepareStatement
			for (int i = 0; i < queryArgs.size(); i++) {
				pstmt.setString(i + 1, queryArgs.get(i));
			}
			System.out.println(pstmt.toString());
        	
			// Execute query
			ResultSet rs = pstmt.executeQuery();
			
			// Add each product to result list
			while (rs.next()) {
				Product prod = buildProduct(rs);
				results.add(prod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        return results;
    }
}
