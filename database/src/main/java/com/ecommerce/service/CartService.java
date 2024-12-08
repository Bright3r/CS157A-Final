package com.ecommerce.service;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    private List<CartItem> cartItems = new ArrayList<>();

    // The method expects individual parameters instead of a CartItem object
    public void addToCart(CartItem cartItem) {
        // Check if the product already exists in the cart
        for (CartItem item : cartItems) {
            if (item.getProductID().equals(cartItem.getProductID())) {
                // Update quantity if the item already exists
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                return;
            }
        }
        // Add the new CartItem
        cartItems.add(cartItem);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void removeFromCart(Integer productID) {
        cartItems.removeIf(item -> item.getProductID().equals(productID));
    }

    public Double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
