package com.bff.reservation.thirdparty.dto;
import lombok.Data;

import java.util.Date;

@Data
public class TptNotificationRequest {
    private Long userId;
    private String message;
    private Date notificationDate;
}
