package com.ecommerce.controller;

import com.ecommerce.model.Payment;
import com.ecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    @GetMapping("/{paymentId}")
    public Payment getPayment(@PathVariable Integer paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @PutMapping("/{paymentId}/status")
    public Payment updatePaymentStatus(@PathVariable Integer paymentId, @RequestParam String status) {
        return paymentService.updatePaymentStatus(paymentId, status);
    }
}
