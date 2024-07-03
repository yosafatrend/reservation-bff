package com.bff.reservation.mobile.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MobileNotificationDetailResponse {
    private Long notificationId;
    private String message;
    private Date notificationDate;
    private String status;
}
