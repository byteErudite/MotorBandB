package com.vaibhav.parkingReservation.requests;

import com.vaibhav.parkingReservation.constants.constantEntity.NotificationType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;
import java.util.Set;

public class NotificationSearchRequest {
    private Set<String> notificationIds;
    private Timestamp createdAt;
    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private Set<NotificationType> notificationTypes;
    private Boolean isRead;
    private Set<String> userIds;

    public NotificationSearchRequest() {
    }

    public Set<String> getNotificationIds() {
        return notificationIds;
    }

    public void setNotificationIds(Set<String> notificationIds) {
        this.notificationIds = notificationIds;
    }

    public Set<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<String> userIds) {
        this.userIds = userIds;
    }

    public Set<NotificationType> getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(Set<NotificationType> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotificationSearchRequest{");
        sb.append("notificationIds=").append(notificationIds);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", notificationTypes=").append(notificationTypes);
        sb.append(", isRead=").append(isRead);
        sb.append(", userIds=").append(userIds);
        sb.append('}');
        return sb.toString();
    }
}
