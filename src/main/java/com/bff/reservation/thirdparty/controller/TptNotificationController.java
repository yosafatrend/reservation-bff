package com.bff.reservation.thirdparty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.thirdparty.dto.TptNotificationRequest;
import com.bff.reservation.thirdparty.dto.TptNotificationResponse;
import com.bff.reservation.thirdparty.service.TptNotificationService;

@RestController
@RequestMapping("/api/thirdparty/notifications")
public class TptNotificationController {

    @Autowired
    private TptNotificationService thirdPartyNotificationService;

    @PostMapping
    public ResponseEntity<TptNotificationResponse> createNewNotification(@RequestBody TptNotificationRequest notificationRequestDTO) {
        TptNotificationResponse notificationResponseDTO = thirdPartyNotificationService.createNewNotification(notificationRequestDTO);
        return ResponseEntity.ok(notificationResponseDTO);
    }
}

