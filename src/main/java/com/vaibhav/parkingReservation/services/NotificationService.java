package com.vaibhav.parkingReservation.services;

import com.vaibhav.parkingReservation.DTOs.NotificationDTO;
import com.vaibhav.parkingReservation.entity.Notification;
import com.vaibhav.parkingReservation.requests.NotificationSearchRequest;
import com.vaibhav.parkingReservation.response.NotificationSearchResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface NotificationService {

    NotificationSearchResponse getNotifications(NotificationSearchRequest notificationSearchRequest, int page, int size, String sort);
    Map<String, List<NotificationDTO>> createNotification(Set<NotificationDTO> notifications);
    String deleteNotifications(List<UUID> notificationIds);

}
