package com.bff.reservation.mobile.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MobilePaymentResponse {
    private Long paymentId;
    private Double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String status;
}
