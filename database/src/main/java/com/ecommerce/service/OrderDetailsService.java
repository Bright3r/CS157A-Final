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
        return null;
    }

    public Optional<OrderDetails> getOrderDetailsById(Integer orderDetailsId) {
    	return null;
    }

    public OrderDetails addOrderDetails(OrderDetails orderDetails) {
    	return null;
    }

    public OrderDetails updateOrderDetails(OrderDetails orderDetails) {
    	return null;
    }

    public void deleteOrderDetails(Integer orderDetailsId) {
    	
    }
}
