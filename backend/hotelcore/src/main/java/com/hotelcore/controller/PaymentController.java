package com.hotelcore.controller;

import com.hotelcore.dto.PaymentRequest;
import com.hotelcore.dto.PaymentResponse;
import com.hotelcore.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentResponse> getAllPayment() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id) {
        return paymentService.findById(id);
    }

    @PostMapping
    public PaymentResponse createPayment(@RequestBody PaymentRequest request) {
        return paymentService.savePayment(request);
    }

    @PutMapping("/{id}")
    public PaymentResponse update(@PathVariable Long id,
                                  @RequestBody PaymentRequest request) {
        return paymentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentById(@PathVariable Long id){
        paymentService.delete(id);
    }
}
