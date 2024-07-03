package com.bff.reservation.thirdparty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Notifications;
import com.bff.reservation.common.model.StatusNotification;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.repository.NotificationRepository;
import com.bff.reservation.common.repository.UserRepository;
import com.bff.reservation.thirdparty.dto.TptNotificationRequest;
import com.bff.reservation.thirdparty.dto.TptNotificationResponse;

@Service
public class TptNotificationService {

    @Autowired
    private NotificationRepository notificationsRepository;

    @Autowired
    private UserRepository usersRepository;

    public TptNotificationResponse createNewNotification(TptNotificationRequest notificationRequestDTO) {
        Notifications notification = new Notifications();
        notification.setMessage(notificationRequestDTO.getMessage());
        notification.setNotification_data(notificationRequestDTO.getNotificationDate());
        notification.setStatus(StatusNotification.unread);

        Users user = usersRepository.findById(notificationRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        notification.setUsers(user);

        Notifications savedNotification = notificationsRepository.save(notification);
        return convertToResponse(savedNotification);
    }

    private TptNotificationResponse convertToResponse(Notifications notification) {
        TptNotificationResponse response = new TptNotificationResponse();
        response.setNotificationId(notification.getNotification_id());
        response.setUserId(notification.getUsers().getUser_id());
        response.setMessage(notification.getMessage());
        response.setNotificationDate(notification.getNotification_data());
        response.setStatus(notification.getStatus().toString());
        return response;
    }
}

