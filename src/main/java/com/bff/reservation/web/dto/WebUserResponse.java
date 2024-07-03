package com.bff.reservation.web.dto;

import java.util.Date;

import com.bff.reservation.common.model.Role;

import lombok.Data;

@Data
public class WebUserResponse {
    private Long userId;
    private String name;
    private String noTelp;
    private String email;
    private Role role;
    private Date createdAt;
    private Date updatedAt;
}
