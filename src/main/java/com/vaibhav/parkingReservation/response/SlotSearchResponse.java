package com.vaibhav.parkingReservation.response;

import com.vaibhav.parkingReservation.DTOs.SlotDTO;

import java.io.Serializable;
import java.util.List;

public class SlotSearchResponse extends ResponseHandler implements Serializable {

    private List<SlotDTO> slots;

    public SlotSearchResponse(Page page, List<SlotDTO> slots) {
        super(page);
        this.slots = slots;
    }

    public SlotSearchResponse(List<SlotDTO> slots) {
        this.slots = slots;
    }

    public List<SlotDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotDTO> slots) {
        this.slots = slots;
    }
}
