package com.ecommerce.repository;

import com.ecommerce.model.OrderDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDetailsRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderDetailsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Method to get an OrderDetail by its ID
    public OrderDetails getOrderDetailById(int orderDetailID) {
        String sql = "SELECT * FROM OrderDetails WHERE orderDetailID = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<OrderDetails>() {
            @Override
            public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderDetailID(rs.getInt("orderDetailID"));
                orderDetails.setOrderID(rs.getInt("orderID"));
                orderDetails.setProductID(rs.getInt("productID"));
                orderDetails.setQuantity(rs.getInt("quantity"));
                orderDetails.setPrice(rs.getDouble("price"));
                return orderDetails;
            }
        }, orderDetailID);
    }

    // Method to save an OrderDetail
    public void saveOrderDetail(OrderDetails orderDetails) {
        String sql = "INSERT INTO OrderDetails (orderID, productID, quantity, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                orderDetails.getOrderID(),
                orderDetails.getProductID(),
                orderDetails.getQuantity(),
                orderDetails.getPrice());
    }

    // Method to update an OrderDetail
    public void updateOrderDetail(OrderDetails orderDetails) {
        String sql = "UPDATE OrderDetails SET orderID = ?, productID = ?, quantity = ?, price = ? WHERE orderDetailID = ?";
        jdbcTemplate.update(sql,
                orderDetails.getOrderID(),
                orderDetails.getProductID(),
                orderDetails.getQuantity(),
                orderDetails.getPrice(),
                orderDetails.getOrderDetailID());
    }

    // Method to delete an OrderDetail
    public void deleteOrderDetail(int orderDetailID) {
        String sql = "DELETE FROM OrderDetails WHERE orderDetailID = ?";
        jdbcTemplate.update(sql, orderDetailID);
    }

    // Method to fetch all OrderDetails
    public List<OrderDetails> getAllOrderDetails() {
        String sql = "SELECT * FROM OrderDetails";
        return jdbcTemplate.query(sql, new RowMapper<OrderDetails>() {
            @Override
            public OrderDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderDetailID(rs.getInt("orderDetailID"));
                orderDetails.setOrderID(rs.getInt("orderID"));
                orderDetails.setProductID(rs.getInt("productID"));
                orderDetails.setQuantity(rs.getInt("quantity"));
                orderDetails.setPrice(rs.getDouble("price"));
                return orderDetails;
            }
        });
    }
}
