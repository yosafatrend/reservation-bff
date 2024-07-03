package com.bff.reservation.web.dto;

import java.util.Date;

import com.bff.reservation.common.model.StatusPayment;

import lombok.Data;

@Data
public class WebPaymentRequest {
    private Long reservationId;
    private Double amount;
    private Date paymentDate;
    private String paymentMethod;
    private StatusPayment status;

}

