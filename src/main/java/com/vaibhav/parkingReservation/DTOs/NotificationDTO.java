package com.vaibhav.parkingReservation.DTOs;

import com.vaibhav.parkingReservation.constants.constantEntity.NotificationType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;
import java.util.UUID;

public class NotificationDTO {

    private UUID notificationId;
    private String message;
    private Timestamp createdAt;
    private boolean isDeleted;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    private boolean isRead;
    private UUID userId;
    private UserDTO user;

    public NotificationDTO() {
    }

    public UUID getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public NotificationDTO(UUID notificationId, String message, Timestamp createdAt, boolean isDeleted, NotificationType notificationType, boolean isRead, UUID userId) {
        this.notificationId = notificationId;
        this.message = message;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.notificationType = notificationType;
        this.isRead = isRead;
        this.userId = userId;
    }
}
