package com.bff.reservation.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Notifications;
import com.bff.reservation.common.repository.NotificationRepository;
import com.bff.reservation.common.repository.UserRepository;
import com.bff.reservation.web.dto.WebNotificationsRequest;
import com.bff.reservation.web.dto.WebNotificationsResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WebNotificationsService {

    @Autowired
    private NotificationRepository notificationsRepository;

    @Autowired
    private UserRepository usersRepository;

    public List<WebNotificationsResponse> getAllNotifications() {
        return notificationsRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public WebNotificationsResponse getNotificationById(Long id) {
        Optional<Notifications> notification = notificationsRepository.findById(id);
        return notification.map(this::convertToResponse).orElse(null);
    }

    public WebNotificationsResponse createNotification(WebNotificationsRequest notificationRequest) {
        Notifications notification = convertToEntity(notificationRequest);
        Notifications createdNotification = notificationsRepository.save(notification);
        return convertToResponse(createdNotification);
    }

    public WebNotificationsResponse updateNotification(Long id, WebNotificationsRequest notificationRequest) {
        Optional<Notifications> optionalNotification = notificationsRepository.findById(id);
        if (optionalNotification.isPresent()) {
            Notifications existingNotification = optionalNotification.get();
            existingNotification.setUsers(usersRepository.findById(notificationRequest.getUserId()).orElse(null));
            existingNotification.setMessage(notificationRequest.getMessage());
            existingNotification.setNotification_data(notificationRequest.getNotificationDate());
            existingNotification.setStatus(notificationRequest.getStatus());
            Notifications updatedNotification = notificationsRepository.save(existingNotification);
            return convertToResponse(updatedNotification);
        } else {
            return null;
        }
    }

    public boolean deleteNotification(Long id) {
        if (notificationsRepository.existsById(id)) {
            notificationsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Notifications convertToEntity(WebNotificationsRequest notificationRequest) {
        Notifications notification = new Notifications();
        notification.setUsers(usersRepository.findById(notificationRequest.getUserId()).orElse(null));
        notification.setMessage(notificationRequest.getMessage());
        notification.setNotification_data(notificationRequest.getNotificationDate());
        notification.setStatus(notificationRequest.getStatus());
        return notification;
    }

    private WebNotificationsResponse convertToResponse(Notifications notification) {
        WebNotificationsResponse response = new WebNotificationsResponse();
        response.setNotificationId(notification.getNotification_id());
        response.setUserId(notification.getUsers().getUser_id());
        response.setMessage(notification.getMessage());
        response.setNotificationDate(notification.getNotification_data());
        response.setStatus(notification.getStatus());
        return response;
    }
}
