package com.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.ApiResponse;
import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    
    // Get an order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Get orders by user
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Integer userID) {    	
    	List<Order> orders = orderService.getOrdersByUserID(userID);
    	return ResponseEntity.ok(orders);
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody Order order) {
        int result = orderService.createOrder(order);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Order placed successfully", true));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Failed to create order.", false));
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
