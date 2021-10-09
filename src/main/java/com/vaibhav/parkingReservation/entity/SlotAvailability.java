package com.vaibhav.parkingReservation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Table(name = "slotAvailability")
@Entity
public class SlotAvailability extends BaseEntity implements Serializable {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID availabilityId;

    private UUID slotId;
    private Timestamp startTime;
    private Timestamp endTime;

    public SlotAvailability() {
    }

    @ManyToOne
    @JoinColumn(name="slotId", insertable = false, updatable=false)
    private Slot slot;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public SlotAvailability copyObject() {
        return new SlotAvailability(this.availabilityId, this.startTime, this.endTime, this.slot);
    }

    public SlotAvailability(UUID availabilityId, Timestamp startTime, Timestamp endTime, Slot slot) {
        this.availabilityId = availabilityId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slot = slot;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SlotAvailability{");
        sb.append("availabilityId=").append(availabilityId);
        sb.append(", slotId=").append(slotId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", slot=").append(slot);
        sb.append('}');
        return sb.toString();
    }
}
