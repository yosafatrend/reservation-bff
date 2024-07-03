package com.bff.reservation.mobile.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MobileReservationResponse {
    private Long reservationId;
    private Date reservationDate;
    private Date startTime;
    private Date endTime;
    private String status;
}
