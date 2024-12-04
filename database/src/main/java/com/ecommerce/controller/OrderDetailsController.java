package com.ecommerce.controller;

import com.ecommerce.model.OrderDetails;
import com.ecommerce.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping("/orderdetails")
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsService.getAllOrderDetails();
    }

    @GetMapping("/orderdetails/{id}")
    public OrderDetails getOrderDetailsById(@PathVariable Integer id) {
        return orderDetailsService.getOrderDetailsById(id).orElse(null);
    }

    @PostMapping("/orderdetails")
    public OrderDetails addOrderDetails(@RequestBody OrderDetails orderDetails) {
        return orderDetailsService.addOrderDetails(orderDetails);
    }

    @PutMapping("/orderdetails/{id}")
    public OrderDetails updateOrderDetails(@PathVariable Integer id, @RequestBody OrderDetails orderDetails) {
        orderDetails.setOrderDetailsID(id); // Ensure the correct ID is set before saving
        return orderDetailsService.updateOrderDetails(orderDetails);
    }

    @DeleteMapping("/orderdetails/{id}")
    public void deleteOrderDetails(@PathVariable Integer id) {
        orderDetailsService.deleteOrderDetails(id);
    }
}
