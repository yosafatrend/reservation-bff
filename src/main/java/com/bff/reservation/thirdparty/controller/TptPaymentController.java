package com.bff.reservation.thirdparty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.thirdparty.dto.TptPaymentRequest;
import com.bff.reservation.thirdparty.dto.TptPaymentResponse;
import com.bff.reservation.thirdparty.service.TptPaymentService;

@RestController
@RequestMapping("/api/thirdparty/payments")
public class TptPaymentController {

    @Autowired
    private TptPaymentService thirdPartyPaymentService;

    @PostMapping
    public ResponseEntity<TptPaymentResponse> createNewPayment(@RequestBody TptPaymentRequest paymentRequestDTO) {
        TptPaymentResponse paymentResponseDTO = thirdPartyPaymentService.createNewPayment(paymentRequestDTO);
        return ResponseEntity.ok(paymentResponseDTO);
    }
}
