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

    private Float nearestExit;
    private String nearestExitName;
    private String identifier1;//floor
    private String identifier2;//block
    private String identifier3;//row
    private String identifier4;//column
    private boolean isFunctional;
    private boolean isReserved;


    @OneToOne(mappedBy = "slot")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name="parkingGarageId", nullable=false)
    private ParkingGarage parkingGarage;

    public Slot() {
    }


    public String getNearestExitName() {
        return nearestExitName;
    }

    public void setNearestExitName(String nearestExitName) {
        this.nearestExitName = nearestExitName;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
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

    public Float getNearestExit() {
        return nearestExit;
    }

    public void setNearestExit(Float nearestExit) {
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

    public boolean isFunctional() {
        return isFunctional;
    }

    public void setFunctional(boolean functional) {
        isFunctional = functional;
    }

    public String getIdentifier3() {
        return identifier3;
    }

    public void setIdentifier3(String identifier3) {
        this.identifier3 = identifier3;
    }

    public String getIdentifier4() {
        return identifier4;
    }

    public void setIdentifier4(String identifier4) {
        this.identifier4 = identifier4;
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
        sb.append(", nearestExitName='").append(nearestExitName).append('\'');
        sb.append(", identifier1='").append(identifier1).append('\'');
        sb.append(", identifier2='").append(identifier2).append('\'');
        sb.append(", identifier3='").append(identifier3).append('\'');
        sb.append(", indetifier4='").append(identifier4).append('\'');
        sb.append(", isAvailable=").append(isFunctional);
        sb.append(", isReserved=").append(isReserved);
        sb.append(", booking=").append(booking);
        sb.append(", parkingGarage=").append(parkingGarage);
        sb.append('}');
        return sb.toString();
    }
}
