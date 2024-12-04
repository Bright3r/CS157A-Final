package com.ecommerce.service;

import com.ecommerce.model.OrderDetails;
import com.ecommerce.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    public Optional<OrderDetails> getOrderDetailsById(Integer orderDetailsId) {
        return orderDetailsRepository.findById(orderDetailsId);
    }

    public OrderDetails addOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public OrderDetails updateOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public void deleteOrderDetails(Integer orderDetailsId) {
        orderDetailsRepository.deleteById(orderDetailsId);
    }
}
