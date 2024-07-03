package com.bff.reservation.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Notifications;
import com.bff.reservation.common.model.StatusNotification;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.repository.NotificationRepository;
import com.bff.reservation.common.repository.UserRepository;
import com.bff.reservation.mobile.dto.MobileNotificationDetailResponse;
import com.bff.reservation.mobile.dto.MobileNotificationResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobileNotificationService {

    @Autowired
    private NotificationRepository notificationsRepository;

    @Autowired
    private UserRepository usersRepository;

    public List<MobileNotificationResponse> getUserNotifications(String username) {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Notifications> notifications = notificationsRepository.findByUsers(user);
        return notifications.stream()
                .map(this::convertToSummaryResponse)
                .collect(Collectors.toList());
    }

    public MobileNotificationDetailResponse markNotificationAsRead(String username, Long id) {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        Notifications notification = notificationsRepository.findByIdAndUsers(id, user)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setStatus(StatusNotification.read);
        Notifications updatedNotification = notificationsRepository.save(notification);
        return convertToDetailResponse(updatedNotification);
    }

    private MobileNotificationResponse convertToSummaryResponse(Notifications notification) {
        MobileNotificationResponse response = new MobileNotificationResponse();
        response.setNotificationId(notification.getNotification_id());
        response.setMessage(notification.getMessage());
        response.setNotificationDate(notification.getNotification_data());
        response.setStatus(notification.getStatus().toString());
        return response;
    }

    private MobileNotificationDetailResponse convertToDetailResponse(Notifications notification) {
        MobileNotificationDetailResponse response = new MobileNotificationDetailResponse();
        response.setNotificationId(notification.getNotification_id());
        response.setMessage(notification.getMessage());
        response.setNotificationDate(notification.getNotification_data());
        response.setStatus(notification.getStatus().toString());
        return response;
    }
}
