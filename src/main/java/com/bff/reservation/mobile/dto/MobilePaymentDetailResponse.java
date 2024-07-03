package com.bff.reservation.mobile.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MobilePaymentDetailResponse {
    private Long paymentId;
    private Double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String status;
    private Long reservationId;
}
