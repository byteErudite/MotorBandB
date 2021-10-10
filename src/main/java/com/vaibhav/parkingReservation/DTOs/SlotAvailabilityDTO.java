package com.vaibhav.parkingReservation.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlotAvailabilityDTO {

    private UUID availabilityId;
    private UUID slotId;
    private Timestamp startTime;
    private Timestamp endTime;

    public SlotAvailabilityDTO(UUID availabilityId, UUID slotId, Date startTime, Date endTime) {
        this.availabilityId = availabilityId;
        this.slotId = slotId;
        this.startTime = new Timestamp(startTime.getTime());
        this.endTime = new Timestamp(endTime.getTime());
    }

    public SlotAvailabilityDTO() {
    }

    public UUID getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(UUID availabilityId) {
        this.availabilityId = availabilityId;
    }

    public UUID getSlotId() {
        return slotId;
    }

    public void setSlotId(UUID slotId) {
        this.slotId = slotId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
