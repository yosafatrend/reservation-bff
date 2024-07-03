package com.bff.reservation.mobile.dto;

import lombok.Data;

@Data
public class MobileVenueResponse {
    private Long venueId;
    private String name;
    private String address;
    private Double pricePerHour;
}
