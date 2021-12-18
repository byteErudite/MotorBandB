package com.vaibhav.parkingReservation.entity;

import com.vaibhav.parkingReservation.constants.constantEntity.NotificationType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID notificationId;
    private String message;
    private Timestamp createdAt;
    private boolean isDeleted;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    private boolean isRead;

    @ManyToOne
    @JoinColumn(name="userId", insertable = false, updatable=false)
    private User user;

    public Notification() {
    }



    public Notification addMessage(String message) {
        this.message = message;
        return this;
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

    public Notification forUser(User user) {
        this.user = user;
        return this;
    }

    public Notification delete() {
        this.isDeleted = true;
        return this;
    }

    public Notification isRead() {
        this.isRead = true;
        return this;
    }

    public Notification setType(NotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public Notification create() {

        this.createdAt = Timestamp.from(Instant.now());
        this.notificationId = UUID.randomUUID();
        return this;
    }
}
