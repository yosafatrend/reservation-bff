package com.bff.reservation.thirdparty.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class TptReservationResponse {
    private Long reservationId;
    private Date reservationDate;
    private Date startTime;
    private Date endTime;
    private String status;
    private Set<Long> userIds;
    private Set<Long> venueIds;
}

