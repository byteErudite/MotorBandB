package com.vaibhav.parkingReservation.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    @ManyToOne
    @JoinColumn(name="slotTypeId", nullable=false)
    private SlotType slotType;

    private String nearestExit;
    private String identifier1;//floor
    private String identifier2;//block
    private String identifier3;//row
    private String indetifier4;//column
    private boolean isAvailable;

    @OneToOne(mappedBy = "slot")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name="parkingGarageId", nullable=false)
    private ParkingGarage parkingGarage;

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

    public String getIdentifier2() {
        return identifier2;
    }

    public void setIdentifier2(String identifier2) {
        this.identifier2 = identifier2;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getIdentifier3() {
        return identifier3;
    }

    public void setIdentifier3(String identifier3) {
        this.identifier3 = identifier3;
    }

    public String getIndetifier4() {
        return indetifier4;
    }

    public void setIndetifier4(String indetifier4) {
        this.indetifier4 = indetifier4;
    }

    public ParkingGarage getParkingGarage() {
        return parkingGarage;
    }

    public void setParkingGarage(ParkingGarage parkingGarage) {
        this.parkingGarage = parkingGarage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Slot{");
        sb.append("slotId=").append(slotId);
        sb.append(", slotType=").append(slotType);
        sb.append(", nearestExit='").append(nearestExit).append('\'');
        sb.append(", identifier1='").append(identifier1).append('\'');
        sb.append(", indetifier2='").append(identifier2).append('\'');
        sb.append(", isAvailable=").append(isAvailable);
        sb.append('}');
        return sb.toString();
    }
}
