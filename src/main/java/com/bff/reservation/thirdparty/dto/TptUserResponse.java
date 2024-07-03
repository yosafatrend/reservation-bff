package com.bff.reservation.thirdparty.dto;

import lombok.Data;

@Data
public class TptUserResponse {
    private Long userId;
    private String name;
    private String noTelp;
    private String email;
}
