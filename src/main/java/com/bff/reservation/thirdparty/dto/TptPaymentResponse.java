package com.bff.reservation.thirdparty.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TptPaymentResponse {
    private Long paymentId;
    private Long reservationId;
    private Double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String status;
}
