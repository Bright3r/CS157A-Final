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

    // Method to build a frontend order object from a database order object
    private Order buildOrder(ResultSet rs) throws SQLException {
		Order curr = new Order();
		
		// Set fields of Order to match database order
		int orderID = rs.getInt("orderID");
	    curr.setOrderID(orderID);
	    curr.setNumProductsOrdered(rs.getInt("numProductsOrdered"));
	    curr.setDateOrdered(rs.getDate("dateOrdered"));
	    curr.setTotalCost(rs.getDouble("totalCost"));

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
    			curr.setQuantityOrdered(quantityOrdered);
    			
    			// Query for ordered product
    			int productID = rs.getInt("productID");
    			Product product = productService.getProductById(productID).orElse(null);
    			curr.setProduct(product);
    			
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
		
		// Query to get all orders for a user by userID
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
    
    // Update the stock information of ordered products
    private void updateOrderedProducts(Connection conn, Order order, List<CartItem> items) throws SQLException {
		// Query to update a product's stock quantity by productID
		String reduceStockSQL = "UPDATE Products SET quantity = ? WHERE productID = ?";
		PreparedStatement reduceStockPstmt = conn.prepareStatement(reduceStockSQL);
		
		// Reduce stock quantity of each product
		for (CartItem item : items) {
			// Get item from database
			int prodID = item.getProduct().getProductID();
			Product prod = productService.getProductById(prodID).orElse(null);
			// Make sure ordered product exists
			if (prod == null) {
				throw new RuntimeException("Could not find ordered product");
			}
			
    		// Subtract quantity purchased from product's stock quantity
			//
			int newStockQty = prod.getQuantity() - item.getQuantityOrdered();
			
			// Check that stock quantity is not negative
			if (newStockQty < 0) {
				throw new RuntimeException("Insufficient stock to fulfill order");
			}
			
			// Execute Query
			reduceStockPstmt.setInt(1, newStockQty);
			reduceStockPstmt.setInt(2, prodID);
			
			// Make sure product's stock quantity is reduced
			if (reduceStockPstmt.executeUpdate() <= 0) {
				throw new RuntimeException("Failed to update product's stock quantity");
			}
		}
    }
    
    // Calculate the total cost of an order
    private double calculateTotalCost(Connection conn, Order order, List<CartItem> items) throws SQLException {
		// Calculate total cost of order
		double totalCost = 0;
		for (CartItem item : items) {
			// Get item from database
			int prodID = item.getProduct().getProductID();
			Product prod = productService.getProductById(prodID).orElse(null);
			// Make sure ordered product exists
			if (prod == null) {
				throw new RuntimeException("Could not find ordered product");
			}
			
			// Get stored cost of item
			double itemCost = prod.getPrice();
			
			// Add cost of item to totalCost
			//
			int itemQty = item.getQuantityOrdered();
			totalCost += (itemCost * itemQty);
		}
		
		// Check that we do not give the customer free money
		if (totalCost < 0) {
			throw new RuntimeException("Order cannot have negative cost");
		}
		
		return totalCost;
    }
    
    // Insert a new order
    public int saveOrder(Order order) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		
    	try {
        	// Create a transaction for order
    		conn.setAutoCommit(false);
    		
    		// Make sure user is in database
    		Optional<User> orderUser = userService.getUserById(order.getUser().getUserID());
    		if (orderUser.isEmpty()) {
    			// Submitted user not found
    			throw new RuntimeException("Could not find order user");
    		}
    		
    		// Update the stock information for each ordered product
    		List<CartItem> items = order.getProducts();
    		updateOrderedProducts(conn, order, items);
    		
    		
    		// Insert the order into the Orders table
    		//
        	String orderSQL = "INSERT INTO Orders (userID, numProductsOrdered, dateOrdered, shippingAddressID, totalCost) VALUES (?, ?, ?, ?, ?) RETURNING orderID";
    		PreparedStatement pstmt = conn.prepareStatement(orderSQL);
    		pstmt.setInt(1, order.getUser().getUserID());
    		pstmt.setInt(2, order.getNumProductsOrdered());
    		pstmt.setDate(3, order.getDateOrdered());
    		
    		// Set shipping address to this user's stored address
    		pstmt.setInt(4, orderUser.get().getAddress().getAddrID());
    		
    		// Set cost to the calculated total cost for the order
    		double totalCost = calculateTotalCost(conn, order, items);
    		pstmt.setDouble(5, totalCost);
    		
    		// Add order to database
    		ResultSet rs1 = pstmt.executeQuery();
    		if (!rs1.next()) {
    			// Failed to add order
    			throw new RuntimeException("Failed to add order");
    		}
    		
    		// Get orderID of added order
    		int orderID = rs1.getInt("orderID");
    		

    		// Insert each Cart Item into the OrdersDetails table
    		//
    		String orderDetailSQL = "INSERT INTO OrdersDetails (orderID, productID, quantityOrdered) VALUES (?, ?, ?) RETURNING orderDetailsID";
    	    PreparedStatement detailsPstmt = conn.prepareStatement(orderDetailSQL);
    		for (CartItem item : items) {
    			System.out.println("Cart Item Product: " + item.getProduct().getProductName());
    			System.out.println("Cart Item Qty: " + item.getQuantityOrdered());
    			
    			detailsPstmt.setInt(1, orderID);
    			detailsPstmt.setInt(2, item.getProduct().getProductID());
    			detailsPstmt.setInt(3, item.getQuantityOrdered());
    			
    			// Add OrderDetail to database
    			ResultSet rs2 = detailsPstmt.executeQuery();
    			if (!rs2.next()) {
    				throw new RuntimeException("Failed to add order detail");
    			}
    		}
    		
    		// Commit Transaction
    		conn.commit();
    		conn.setAutoCommit(true);
    		return 1;
		} catch (Exception e) {
			// Try to rollback transaction if anything fails
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
			e.printStackTrace();
		}
    	
    	return 0;	// Return 0 if failed to create order
    }


    // Delete an order by ID
    public int deleteById(Integer orderID) {
    	// Get Singleton Database Connection
    	Connection conn = DatabaseConnection.getConnection();
		
    	// Query to delete rows from orders table
        String sql = "DELETE FROM Orders WHERE orderID = ?";
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setInt(1, orderID);
    		
    		return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return 0;
    }
}
