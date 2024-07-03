package com.bff.reservation.web.controller;

import com.bff.reservation.web.dto.WebPaymentRequest;
import com.bff.reservation.web.dto.WebPaymentResponse;
import com.bff.reservation.web.service.WebPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/web/payments")
public class WebPaymentController {

    @Autowired
    private WebPaymentService paymentsService;

    @GetMapping
    public ResponseEntity<List<WebPaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentsService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebPaymentResponse> getPaymentById(@PathVariable Long id) {
        WebPaymentResponse payment = paymentsService.getPaymentById(id);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<WebPaymentResponse> createPayment(@RequestBody WebPaymentRequest paymentRequest) {
        WebPaymentResponse createdPayment = paymentsService.createPayment(paymentRequest);
        return ResponseEntity.ok(createdPayment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebPaymentResponse> updatePayment(@PathVariable Long id, @RequestBody WebPaymentRequest paymentRequest) {
        WebPaymentResponse updatedPayment = paymentsService.updatePayment(id, paymentRequest);
        if (updatedPayment != null) {
            return ResponseEntity.ok(updatedPayment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        boolean isDeleted = paymentsService.deletePayment(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

