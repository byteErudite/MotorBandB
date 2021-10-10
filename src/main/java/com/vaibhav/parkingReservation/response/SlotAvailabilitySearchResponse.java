package com.vaibhav.parkingReservation.response;

import com.vaibhav.parkingReservation.DTOs.SlotAvailabilityDTO;
import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;

import java.io.Serializable;
import java.util.List;

public class SlotAvailabilitySearchResponse extends ResponseHandler implements Serializable {

    private List<SlotAvailabilityDTO> slotAvailabilities;

    public SlotAvailabilitySearchResponse(Page page, List<SlotAvailabilityDTO> slotAvailabilities) {
        super(page);
        this.slotAvailabilities = slotAvailabilities;
    }

    public List<SlotAvailabilityDTO> getSlotTypes() {
        return slotAvailabilities;
    }

    public void setSlotAvailabilities(List<SlotAvailabilityDTO> slotAvailabilities) {
        this.slotAvailabilities = slotAvailabilities;
    }
}
