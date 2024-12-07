package com.ecommerce.service;

import com.ecommerce.model.Payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class PaymentService {

    private Map<Integer, Payment> paymentDatabase = new HashMap<>();

    public Payment processPayment(Payment payment) {
        System.out.println("Processing payment for Order ID: " + payment.getOrderId());
        
        payment.setPaymentStatus("Completed");
        
        paymentDatabase.put(payment.getPaymentId(), payment);
        
        return payment;
    }

    public Payment getPaymentById(Integer paymentId) {
        return paymentDatabase.get(paymentId);
    }

    public Payment updatePaymentStatus(Integer paymentId, String newStatus) {
        Payment payment = paymentDatabase.get(paymentId);
        if (payment != null) {
            payment.setPaymentStatus(newStatus);
        }
        return payment;
    }
}
