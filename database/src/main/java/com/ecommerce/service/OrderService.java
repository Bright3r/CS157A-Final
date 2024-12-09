package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    // Get an order by ID
    public Optional<Order> getOrderById(Integer orderID) {
        return orderRepository.findById(orderID);
    }
    
    // Get orders for a user
    public List<Order> getOrdersByUserID(Integer userID) {
    	return orderRepository.findOrdersByUser(userID);
    }
    
    // Save a new order
    public int createOrder(Order order) {
        return orderRepository.saveOrder(order);
    }

    // Delete an order by ID
    public int deleteOrder(Integer orderID) {
        return orderRepository.deleteById(orderID);
    }
}
