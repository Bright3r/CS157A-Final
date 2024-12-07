package com.ecommerce.controller;

import com.ecommerce.model.CartItem;
import com.ecommerce.service.CartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add item to the cart
    @PostMapping("/add")
    public String addToCart(@RequestBody CartItem cartItem) {
        cartService.addToCart(cartItem);
        return "Item added to cart";
    }

    // Get all items in the cart
    @GetMapping("/items")
    public List<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    // Get total price of items in the cart
    @GetMapping("/total")
    public Double getTotalPrice() {
        return cartService.getTotalPrice();
    }

    // Remove item from the cart
    @DeleteMapping("/remove/{productID}")
    public String removeFromCart(@PathVariable Integer productID) {
        cartService.removeFromCart(productID);
        return "Item removed from cart";
    }

    // Clear the cart
    @DeleteMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "Cart cleared";
    }
}
