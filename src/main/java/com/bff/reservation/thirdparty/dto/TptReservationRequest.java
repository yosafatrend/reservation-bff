package com.bff.reservation.thirdparty.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class TptReservationRequest   {
    private Date reservationDate;
    private Date startTime;
    private Date endTime;
    private Set<Long> userIds;
    private Set<Long> venueIds;
}