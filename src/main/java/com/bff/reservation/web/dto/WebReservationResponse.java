package com.bff.reservation.web.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

import com.bff.reservation.common.model.StatusReservation;

@Data
public class WebReservationResponse {
    private Long reservationId;
    private Set<Long> userIds;
    private Set<Long> venueIds;
    private Date reservationDate;
    private Date startTime;
    private Date endTime;
    private StatusReservation status;
}
