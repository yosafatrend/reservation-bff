package com.bff.reservation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.web.dto.WebNotificationsRequest;
import com.bff.reservation.web.dto.WebNotificationsResponse;
import com.bff.reservation.web.service.WebNotificationsService;

import java.util.List;

@RestController
@RequestMapping("/api/web/notifications")
public class WebNotificationsController {

    @Autowired
    private WebNotificationsService notificationsService;

    @GetMapping
    public ResponseEntity<List<WebNotificationsResponse>> getAllNotifications() {
        return ResponseEntity.ok(notificationsService.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebNotificationsResponse> getNotificationById(@PathVariable Long id) {
        WebNotificationsResponse notification = notificationsService.getNotificationById(id);
        if (notification != null) {
            return ResponseEntity.ok(notification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<WebNotificationsResponse> createNotification(@RequestBody WebNotificationsRequest notificationRequest) {
        WebNotificationsResponse createdNotification = notificationsService.createNotification(notificationRequest);
        return ResponseEntity.ok(createdNotification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebNotificationsResponse> updateNotification(@PathVariable Long id, @RequestBody WebNotificationsRequest notificationRequest) {
        WebNotificationsResponse updatedNotification = notificationsService.updateNotification(id, notificationRequest);
        if (updatedNotification != null) {
            return ResponseEntity.ok(updatedNotification);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        boolean isDeleted = notificationsService.deleteNotification(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

