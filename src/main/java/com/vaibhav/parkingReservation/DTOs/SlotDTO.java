package com.vaibhav.parkingReservation.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SlotDTO {

    private String slotId;
    private Float nearestExit;
    private String nearestExitName;
    private String slotCode;
    private String startDate;
    private String endDate;
    private String identifier1;//floor
    private String identifier2;//block
    private String identifier3;//row
    private String identifier4;//column
    private boolean isFunctional;
    private String slotTypeId;
    private String parkingGarageId;
    private boolean isReserved;

    public SlotDTO(UUID slotId, Float nearestExit, String nearestExitName, String slotCode, Date startDate, Date endDate, String identifier1, String identifier2, String identifier3, String identifier4, boolean isFunctional, UUID slotTypeId, UUID parkingGarageId, boolean isReserved) {
        this.slotId = slotId.toString();
        this.nearestExit = nearestExit;
        this.nearestExitName = nearestExitName;
        this.slotCode = slotCode;
        this.startDate = startDate.toString();
        this.endDate = endDate.toString();
        this.identifier1 = identifier1;
        this.identifier2 = identifier2;
        this.identifier3 = identifier3;
        this.identifier4 = identifier4;
        this.isFunctional = isFunctional;
        this.slotTypeId = slotTypeId.toString();
        this.parkingGarageId = parkingGarageId.toString();
        this.isReserved = isReserved;
    }

    public SlotDTO() {
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSlotTypeId() {
        return slotTypeId;
    }

    public void setSlotTypeId(String slotTypeId) {
        this.slotTypeId = slotTypeId;
    }

    public String getParkingGarageId() {
        return parkingGarageId;
    }

    public void setParkingGarageId(String parkingGarageId) {
        this.parkingGarageId = parkingGarageId;
    }

    public String getSlotCode() {
        return slotCode;
    }

    public void setSlotCode(String slotCode) {
        this.slotCode = slotCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public boolean isFunctional() {
        return isFunctional;
    }

    public void setFunctional(boolean functional) {
        isFunctional = functional;
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
}
