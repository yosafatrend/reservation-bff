package com.bff.reservation.web.dto;

import java.util.Date;

import com.bff.reservation.common.model.StatusNotification;

import lombok.Data;

@Data
public class WebNotificationsResponse {
    private Long notificationId;
    private Long userId;
    private String message;
    private Date notificationDate;
    private StatusNotification status;
}
