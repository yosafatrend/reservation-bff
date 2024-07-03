package com.bff.reservation.mobile.dto;

import lombok.Data;

@Data
public class MobileUserProfileResponse {
    private Long userId;
    private String name;
    private String email;
    private String noTelp;
}
