package com.vaibhav.parkingReservation.response;

import com.vaibhav.parkingReservation.DTOs.NotificationDTO;
import com.vaibhav.parkingReservation.DTOs.ParkingGarageInfo;

import java.io.Serializable;
import java.util.List;

public class NotificationSearchResponse extends ResponseHandler implements Serializable {
    private List<NotificationDTO> notifications;

    public NotificationSearchResponse(Page page, List<NotificationDTO> slots) {
        super(page);
        this.notifications = notifications;
    }

    public List<NotificationDTO> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<ParkingGarageInfo> slotTypes) {
        this.notifications = notifications;
    }
}
