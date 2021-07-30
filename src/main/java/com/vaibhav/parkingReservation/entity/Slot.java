package com.vaibhav.parkingReservation.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Table(name = "slot")
@Entity
public class Slot extends BaseEntity implements Serializable {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID slotId;
    private SlotType slotType;
    private String nearestExit;
    private String identifier1;//floor
    private String indetifier2;//block
    private String identifier3;//row
    private String indetifier4;//column
    private boolean isAvailable;

    public Slot() {
    }

    public UUID getSlotId() {
        return slotId;
    }

    public void setSlotId(UUID slotId) {
        this.slotId = slotId;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }

    public String getNearestExit() {
        return nearestExit;
    }

    public void setNearestExit(String nearestExit) {
        this.nearestExit = nearestExit;
    }

    public String getIdentifier1() {
        return identifier1;
    }

    public void setIdentifier1(String identifier1) {
        this.identifier1 = identifier1;
    }

    public String getIndetifier2() {
        return indetifier2;
    }

    public void setIndetifier2(String indetifier2) {
        this.indetifier2 = indetifier2;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Slot{");
        sb.append("slotId=").append(slotId);
        sb.append(", slotType=").append(slotType);
        sb.append(", nearestExit='").append(nearestExit).append('\'');
        sb.append(", identifier1='").append(identifier1).append('\'');
        sb.append(", indetifier2='").append(indetifier2).append('\'');
        sb.append(", isAvailable=").append(isAvailable);
        sb.append('}');
        return sb.toString();
    }
}
