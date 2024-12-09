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
import org.springframework.stereotype.Repository;

import com.ecommerce.DatabaseConnection;
import com.ecommerce.model.Address;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.service.AddressService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

@Repository
public class OrderRepository {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AddressService addressService;
    
    @Autowired
    private ProductService productService;

    private Order buildOrder(ResultSet rs) throws SQLException {
		Order curr = new Order();
		
		// Set fields of Order to match database order
		int orderID = rs.getInt("orderID");
	    curr.setOrderID(orderID);
	    curr.setNumProductsOrdered(rs.getInt("numProductsOrdered"));
	    curr.setDateOrdered(rs.getDate("dateOrdered"));

	    // Query for order's user
	    int userID = rs.getInt("userID");
	    User user = userService.getUserById(userID).orElse(null);
	    curr.setUser(user);
	    
	    // Query for order's shipping address
	    int addrID = rs.getInt("shippingAddressID");
	    Address addr = addressService.getAddressById(addrID).orElse(null);
	    curr.setShippingAddress(addr);
	    
	    // Query for products in order
	    List<CartItem> orderedProducts = findOrderProducts(orderID);
	    curr.setProducts(orderedProducts);
		
		return curr;
    }
    
    // Find the products in an order
    private List<CartItem> findOrderProducts(int orderID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	List<CartItem> products = new ArrayList<>();
    	
    	// Create a prepared statement to query for orderDetails by orderID
    	String query = "SELECT * FROM OrdersDetails WHERE orderID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		// Set orderID field of prepared statement query
    		pstmt.setInt(1, orderID);
    		
    		// Execute Query
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Add each order details product to the cart items list
    		while (rs.next()) {
    			CartItem curr = new CartItem();

    			// Get ordered quantity of this product
    			int quantityOrdered = rs.getInt("quantityOrdered");
    			curr.setQuantity(quantityOrdered);
    			
    			// Query for ordered product
    			int productID = rs.getInt("productID");
    			Product product = productService.getProductById(productID).orElse(null);
    			curr.setProduct(product);
    			
    			// Calculate price of cart item
    			curr.setPrice(product.getPrice() * quantityOrdered);
    			
    			products.add(curr);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	// Return the ordered products if orderID was in database,
    	// Otherwise return null
    	return products;
    }

    // Find all orders
    public List<Order> findAll() {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		List<Order> orders = new ArrayList<>();
		
    	try {
    		// Query for all orders in database
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Orders");
			while (rs.next()) {
				// Create an Order object for each row in the ResultSet
				Order curr = buildOrder(rs);
				orders.add(curr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	// Return a list of every order
    	return orders;
    }
    
    // Find an order by ID
    public Optional<Order> findById(Integer orderID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
    	Order order = null;
    	
    	// Create a prepared statement to query for order by id
    	String query = "SELECT * FROM Orders WHERE orderID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		// Set orderID field of prepared statement query
    		pstmt.setInt(1, orderID);
    		
    		// Execute Query
    		ResultSet rs = pstmt.executeQuery();
    		
    		// Only one row since orderID is primary key
    		// So build order from the first row of ResultSet
    		rs.next();
    		order = buildOrder(rs);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	// Return the Order if orderID was in database,
    	// Otherwise return null
    	return Optional.ofNullable(order);
    }
    
    // Find orders by userID
    public List<Order> findOrdersByUser(Integer userID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		List<Order> orders = new ArrayList<>();
		
		String query = "SELECT * FROM Orders WHERE userID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    		pstmt.setInt(1, userID);
    		
    		ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// Create an Order object for each row in the ResultSet
				Order curr = buildOrder(rs);
				orders.add(curr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	// Return a list of user's orders
    	return orders;
    }

    // Delete an order by ID
    public int deleteById(Integer orderID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		
        String sql = "DELETE FROM Orders WHERE orderID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, orderID);
    		
    		return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return 0;
    }
    
    // Insert a new order
    public int saveOrder(Order order) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		
    	String sql = "INSERT INTO Orders (userID, numProductsOrdered, dateOrdered, shippingAddressID) VALUES (?, ?, ?, ?)";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, order.getUser().getUserID());
    		pstmt.setInt(2, order.getNumProductsOrdered());
    		pstmt.setDate(3, order.getDateOrdered());
    		pstmt.setInt(4, order.getShippingAddress().getAddrID());
    		
    		return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return 0;
    }
}
