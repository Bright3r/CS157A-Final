package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Create a new order
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        int result = orderService.createOrder(order);
        if (result > 0) {
            return ResponseEntity.ok("Order created successfully");
        } else {
            return ResponseEntity.status(500).body("Failed to create order");
        }
    }

    // Get an order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Delete an order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        int result = orderService.deleteOrder(id);
        if (result > 0) {
            return ResponseEntity.ok("Order deleted successfully");
        } else {
            return ResponseEntity.status(500).body("Failed to delete order");
        }
    }
}
