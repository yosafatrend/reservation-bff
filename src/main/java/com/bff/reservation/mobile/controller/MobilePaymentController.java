package com.bff.reservation.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.mobile.dto.MobilePaymentDetailResponse;
import com.bff.reservation.mobile.dto.MobilePaymentRequest;
import com.bff.reservation.mobile.dto.MobilePaymentResponse;
import com.bff.reservation.mobile.service.MobilePaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/payments")
public class MobilePaymentController {

    @Autowired
    private MobilePaymentService mobilePaymentService;

    @GetMapping
    public ResponseEntity<List<MobilePaymentResponse>> getUserPayments(Authentication authentication) {
        String username = authentication.getName();
        List<MobilePaymentResponse> payments = mobilePaymentService.getUserPayments(username);
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<MobilePaymentDetailResponse> createPayment(
            Authentication authentication,
            @RequestBody MobilePaymentRequest paymentRequest) {
        String username = authentication.getName();
        MobilePaymentDetailResponse payment = mobilePaymentService.createPayment(username, paymentRequest);
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MobilePaymentDetailResponse> updatePayment(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody MobilePaymentRequest paymentRequest) {
        String username = authentication.getName();
        MobilePaymentDetailResponse payment = mobilePaymentService.updatePayment(username, id, paymentRequest);
        return ResponseEntity.ok(payment);
    }
}

