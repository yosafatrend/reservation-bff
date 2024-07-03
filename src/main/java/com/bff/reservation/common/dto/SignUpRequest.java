package com.bff.reservation.common.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String name;
    private String no_telp;
    private String email;
    private String password;
}
