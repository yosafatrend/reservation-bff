package com.bff.reservation.mobile.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MobileReservationDetailResponse {
    private Long reservationId;
    private Date reservationDate;
    private Date startTime;
    private Date endTime;
    private String status;
    private String venueName;
    private String venueAddress;
    private Double venuePricePerHour;
}
