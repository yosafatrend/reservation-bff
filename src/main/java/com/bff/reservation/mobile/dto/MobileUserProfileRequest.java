package com.bff.reservation.mobile.dto;

import lombok.Data;

@Data
public class MobileUserProfileRequest {
    private String name;
    private String noTelp;
    private String email;
}
