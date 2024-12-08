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

    // Save a new order
    public int createOrder(Order order) {
        return orderRepository.saveOrder(order);
    }

    // Get an order by ID
    public Optional<Order> getOrderById(Integer orderID) {
        return orderRepository.findById(orderID);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Delete an order by ID
    public int deleteOrder(Integer orderID) {
        return orderRepository.deleteById(orderID);
    }
}
