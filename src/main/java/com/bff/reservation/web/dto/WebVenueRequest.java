package com.bff.reservation.web.dto;

import lombok.Data;

@Data
public class WebVenueRequest {
    private String name;
    private String address;
    private String facilities;
    private Double pricePerHour;
}
