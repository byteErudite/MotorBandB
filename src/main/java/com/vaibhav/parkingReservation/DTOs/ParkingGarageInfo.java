package com.vaibhav.parkingReservation.DTOs;

import java.util.UUID;

public class ParkingGarageInfo {
    private UUID parkingGarageId;
    private String garageName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pinCode;
    private boolean isPersonalAddress;
    private boolean isOperational;
    private UUID userId;

    public ParkingGarageInfo(UUID parkingGarageId, String garageName, String addressLine1, String addressLine2, String city, String state, String country, String pinCode, boolean isPersonalAddress, boolean isOperational, UUID userId) {
        this.parkingGarageId = parkingGarageId;
        this.garageName = garageName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.isPersonalAddress = isPersonalAddress;
        this.isOperational = isOperational;
        this.userId = userId;
    }

    public UUID getParkingGarageId() {
        return parkingGarageId;
    }

    public void setParkingGarageId(UUID parkingGarageId) {
        this.parkingGarageId = parkingGarageId;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public boolean isPersonalAddress() {
        return isPersonalAddress;
    }

    public void setPersonalAddress(boolean personalAddress) {
        isPersonalAddress = personalAddress;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public void setOperational(boolean operational) {
        isOperational = operational;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
