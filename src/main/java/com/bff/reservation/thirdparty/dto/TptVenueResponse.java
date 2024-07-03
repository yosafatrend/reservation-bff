package com.bff.reservation.thirdparty.dto;

import lombok.Data;

@Data
public class TptVenueResponse {
    private Long venueId;
    private String name;
    private String address;
    private String facilities;
    private Double pricePerHour;
}
