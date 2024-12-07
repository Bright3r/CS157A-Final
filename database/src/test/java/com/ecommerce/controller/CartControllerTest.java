package com.ecommerce.controller;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;
import com.ecommerce.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddToCart() {
        // Creating a mock user to associate with CartItem
        User user = new User();
        user.setUserID(1); // Set user ID as an example

        // Create CartItem with user, productID, quantity, and price
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProductID(1);
        cartItem.setQuantity(2);
        cartItem.setPrice(50.0);

        // Mock cartService behavior for the void method addToCart
        doNothing().when(cartService).addToCart(cartItem); // Use doNothing() since addToCart is a void method

        // Call the controller method
        String response = cartController.addToCart(cartItem);

        // Validate the response and verify interactions
        assertEquals("Item added to cart", response);
        verify(cartService, times(1)).addToCart(cartItem); // Verify that addToCart was called once
    }

    @Test
    public void testGetCartItems() {
        // Add a test for fetching cart items here
        // You would mock cartService.getCartItems() and verify the return value
    }

    // Add more tests for other methods like getTotalPrice(), removeFromCart(), etc.
}
