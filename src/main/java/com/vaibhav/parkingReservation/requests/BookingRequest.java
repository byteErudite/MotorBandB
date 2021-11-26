package com.vaibhav.parkingReservation.requests;

import com.vaibhav.parkingReservation.entity.SlotAvailability;

public class BookingRequest {

    private String startDateTime;
    private String endDateTime;
    private String userId;
    private String parkingGarageId;
    private String slotId;
    private SlotAvailability slotAvailability;


    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParkingGarageId() {
        return parkingGarageId;
    }

    public void setParkingGarageId(String parkingGarageId) {
        this.parkingGarageId = parkingGarageId;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public SlotAvailability getSlotAvailability() {
        return slotAvailability;
    }

    public void setSlotAvailability(SlotAvailability slotAvailability) {
        this.slotAvailability = slotAvailability;
    }
}
