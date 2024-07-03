package com.bff.reservation.web.dto;

import java.util.Date;

import com.bff.reservation.common.model.StatusNotification;

import lombok.Data;

@Data
public class WebNotificationsRequest {
    private Long userId;
    private String message;
    private Date notificationDate;
    private StatusNotification status;

    // Getters and Setters
}

