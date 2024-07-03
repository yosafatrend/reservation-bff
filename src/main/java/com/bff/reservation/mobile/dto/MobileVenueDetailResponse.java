package com.bff.reservation.mobile.dto;

import lombok.Data;

@Data
public class MobileVenueDetailResponse {
    private Long venueId;
    private String name;
    private String address;
    private String facilities;
    private Double pricePerHour;
}
