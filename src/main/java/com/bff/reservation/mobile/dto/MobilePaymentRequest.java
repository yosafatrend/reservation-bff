package com.bff.reservation.mobile.dto;

import lombok.Data;

@Data
public class MobilePaymentRequest {
    private Long reservationId;
    private Double amount;
    private String paymentMethod;
}
