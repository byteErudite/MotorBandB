package com.vaibhav.parkingReservation.producers;

import com.vaibhav.parkingReservation.DTOs.NotificationDTO;
import com.vaibhav.parkingReservation.constants.constantEntity.NotificationType;
import com.vaibhav.parkingReservation.logUtil.CurrentThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {

    private static final String NOTIFICATION_TOPIC = "TestTopic";

    @Autowired
    BaseEventProducer baseEventProducer;

    public void sendActivityNotification(NotificationDTO notification) {
      activity.sendNotification(notification);
    }

    @FunctionalInterface
    interface sendNotification {
        void sendNotification(NotificationDTO notification);
    }

    private sendNotification activity = (NotificationDTO notification) -> {
        final String description = NotificationType.ACTIVITY.toString();
        baseEventProducer.produceEvent(notification, description, CurrentThread.correlationId(), NOTIFICATION_TOPIC);
    };

    private sendNotification alert = (NotificationDTO notification) -> {
        final String description = NotificationType.ALERT.toString();
        baseEventProducer.produceEvent(notification, description, CurrentThread.correlationId(), NOTIFICATION_TOPIC);
    };

    private sendNotification promotion = (NotificationDTO notification) -> {
        final String description = NotificationType.PROMOTION.toString();
        baseEventProducer.produceEvent(notification, description, CurrentThread.correlationId(), NOTIFICATION_TOPIC);
    };

}
