package com.vaibhav.parkingReservation.DTOs;

import java.util.UUID;

public class ParkingGarageDTO {

    private String garageName;
    private AddressDTO address;
    private boolean isOperational;
    private UUID userId;

    public ParkingGarageDTO() {
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
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
