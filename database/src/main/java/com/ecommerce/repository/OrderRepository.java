package com.ecommerce.repository;

import com.ecommerce.model.Order;
import com.ecommerce.model.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Order buildOrder(ResultSet rs) throws SQLException {
		Order curr = new Order();
		
		// Set fields of Order to match database order
	    curr.setOrderID(rs.getInt("orderID"));
	    curr.setUser(null);
	    curr.setNumProductsOrdered(rs.getInt("numProductsOrdered"));
	    curr.setDateOrdered(rs.getDate("dateOrdered"));
	    curr.setShippingAddress(null);
		
		return curr;
    }
    
    // Insert a new order
    public int saveOrder(Order order) {
        String sql = "INSERT INTO Orders (userID, numProductsOrdered, dateOrdered, shippingAddressID) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, order.getUser(), order.getNumProductsOrdered(), order.getDateOrdered(), order.getShippingAddress());
    }

    // Find an order by ID
    public Optional<Order> findById(Integer orderID) {
        String sql = "SELECT * FROM Orders WHERE orderID = ?";
        try {
            Order order = jdbcTemplate.queryForObject(sql, new Object[]{orderID}, new BeanPropertyRowMapper<>(Order.class));
            return Optional.ofNullable(order);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Find all orders
    public List<Order> findAll() {
        String sql = "SELECT * FROM Orders";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    // Delete an order by ID
    public int deleteById(Integer orderID) {
        String sql = "DELETE FROM Orders WHERE orderID = ?";
        return jdbcTemplate.update(sql, orderID);
    }
}
