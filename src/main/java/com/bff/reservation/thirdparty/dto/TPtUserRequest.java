package com.bff.reservation.thirdparty.dto;

import lombok.Data;

@Data
public class TPtUserRequest {
    private String name;
    private String noTelp;
    private String email;
    private String password;
}
