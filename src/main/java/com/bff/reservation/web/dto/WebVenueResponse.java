package com.bff.reservation.web.dto;

import java.util.Date;

import lombok.Data;

@Data
public class WebVenueResponse {
    private Long venueId;
    private String name;
    private String address;
    private String facilities;
    private Double pricePerHour;
    private Date createdAt;
    private Date updatedAt;
}