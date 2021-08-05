package com.vaibhav.parkingReservation.DTOs;

public class SlotSearchRequest {

    String slotId;
    String startDateTime;
    String endDateTime;
    String parkingGarageId;
    String identifier2;
    String identifier1;
    String identifier3;
    String identifier4;
    Boolean isFunctional;
    Boolean isReserved;
    Float nearestExitDistance;
    String exitName;

    public SlotSearchRequest() {
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

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

    public String getParkingGarageId() {
        return parkingGarageId;
    }

    public void setParkingGarageId(String parkingGarageId) {
        this.parkingGarageId = parkingGarageId;
    }

    public String getIdentifier2() {
        return identifier2;
    }

    public void setIdentifier2(String identifier2) {
        this.identifier2 = identifier2;
    }

    public String getIdentifier1() {
        return identifier1;
    }

    public void setIdentifier1(String identifier1) {
        this.identifier1 = identifier1;
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

    public Boolean getFunctional() {
        return isFunctional;
    }

    public void setFunctional(Boolean functional) {
        isFunctional = functional;
    }

    public Boolean getReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public Float getNearestExitDistance() {
        return nearestExitDistance;
    }

    public void setNearestExitDistance(Float nearestExitDistance) {
        this.nearestExitDistance = nearestExitDistance;
    }

    public String getExitName() {
        return exitName;
    }

    public void setExitName(String exitName) {
        this.exitName = exitName;
    }
}
