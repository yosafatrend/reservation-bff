package com.bff.reservation.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.mobile.dto.MobileNotificationDetailResponse;
import com.bff.reservation.mobile.dto.MobileNotificationResponse;
import com.bff.reservation.mobile.service.MobileNotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/notifications")
public class MobileNotificationController {

    @Autowired
    private MobileNotificationService mobileNotificationService;

    @GetMapping
    public ResponseEntity<List<MobileNotificationResponse>> getUserNotifications(Authentication authentication) {
        String username = authentication.getName();
        List<MobileNotificationResponse> notifications = mobileNotificationService.getUserNotifications(username);
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<MobileNotificationDetailResponse> markNotificationAsRead(
            Authentication authentication,
            @PathVariable Long id) {
        String username = authentication.getName();
        MobileNotificationDetailResponse notification = mobileNotificationService.markNotificationAsRead(username, id);
        return ResponseEntity.ok(notification);
    }
}

