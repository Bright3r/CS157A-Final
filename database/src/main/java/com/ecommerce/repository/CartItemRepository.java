package com.ecommerce.repository;

import com.ecommerce.model.CartItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public CartItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CartItem getCartItemById(int id) {
        String sql = "SELECT * FROM CartItems WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (ResultSet rs, int rowNum) -> {
            CartItem cartItem = new CartItem();
            cartItem.setId(rs.getInt("id"));
            cartItem.setUserID(rs.getInt("user_id")); // Foreign key to User
            cartItem.setProductID(rs.getInt("productID"));
            cartItem.setQuantity(rs.getInt("quantity"));
            cartItem.setPrice(rs.getDouble("price"));
            return cartItem;
        }, id);
    }

    public void saveCartItem(CartItem cartItem) {
        String sql = "INSERT INTO CartItems (user_id, productID, quantity, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                cartItem.getUserID(),
                cartItem.getProductID(),
                cartItem.getQuantity(),
                cartItem.getPrice());
    }

    public void updateCartItem(CartItem cartItem) {
        String sql = "UPDATE CartItems SET user_id = ?, productID = ?, quantity = ?, price = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                cartItem.getUserID(),
                cartItem.getProductID(),
                cartItem.getQuantity(),
                cartItem.getPrice(),
                cartItem.getId());
    }

    public void deleteCartItem(int id) {
        String sql = "DELETE FROM CartItems WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<CartItem> getCartItemsByUser(int userID) {
        String sql = "SELECT * FROM CartItems WHERE user_id = ?";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            CartItem cartItem = new CartItem();
            cartItem.setId(rs.getInt("id"));
            cartItem.setUserID(rs.getInt("user_id"));
            cartItem.setProductID(rs.getInt("productID"));
            cartItem.setQuantity(rs.getInt("quantity"));
            cartItem.setPrice(rs.getDouble("price"));
            return cartItem;
        }, userID);
    }
}
