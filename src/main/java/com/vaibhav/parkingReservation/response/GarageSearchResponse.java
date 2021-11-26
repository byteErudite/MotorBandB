package com.vaibhav.parkingReservation.response;

import com.vaibhav.parkingReservation.DTOs.ParkingGarageInfo;

import java.io.Serializable;
import java.util.List;

public class GarageSearchResponse extends ResponseHandler implements Serializable {

    private List<ParkingGarageInfo> slotTypes;

    public GarageSearchResponse(Page page, List<ParkingGarageInfo> slots) {
        super(page);
        this.slotTypes = slots;
    }

    public List<ParkingGarageInfo> getSlotTypes() {
        return slotTypes;
    }

    public void setSlotTypes(List<ParkingGarageInfo> slotTypes) {
        this.slotTypes = slotTypes;
    }
}
