package com.bff.reservation.web.dto;

import lombok.Data;

@Data
public class WebUserRequest {
    private String name;
    private String noTelp;
    private String email;
    private String password;
}
