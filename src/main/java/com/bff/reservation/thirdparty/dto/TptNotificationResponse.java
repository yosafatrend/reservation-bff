package com.bff.reservation.thirdparty.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TptNotificationResponse {
    private Long notificationId;
    private Long userId;
    private String message;
    private Date notificationDate;
    private String status;
}

