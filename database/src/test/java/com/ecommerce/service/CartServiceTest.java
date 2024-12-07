package com.ecommerce.service;

import com.ecommerce.model.CartItem;
import com.ecommerce.repository.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setup() {
        // Initialize mock objects
    }

    @Test
    public void testAddToCart() {
        CartItem cartItem = new CartItem(1, 1, 2, 50.0);
        
        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);  // Mock the repository save method

        cartService.addToCart(cartItem);
        
        verify(cartItemRepository, times(1)).save(cartItem);  // Verify save method was called once
    }

    @Test
    public void testRemoveFromCart() {
        // Add test to remove item from cart
    }
}
