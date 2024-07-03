package com.bff.reservation.mobile.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MobileReservationRequest {
    private Long venueId;
    private Date reservationDate;
    private Date startTime;
    private Date endTime;
}
