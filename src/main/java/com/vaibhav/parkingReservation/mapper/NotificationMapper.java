package com.vaibhav.parkingReservation.mapper;

import com.vaibhav.parkingReservation.DTOs.NotificationDTO;
import com.vaibhav.parkingReservation.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { HibernateAwareMapper.class, UUIDMapper.class})
public interface NotificationMapper {

    Notification notificationDTOToNotification(NotificationDTO notification);
    NotificationDTO notificationToNotificationDTO(Notification notification);
}
