package com.vaibhav.parkingReservation.response;

import com.vaibhav.parkingReservation.entity.Slot;

import java.io.Serializable;
import java.util.List;

public class SlotSearchResponse extends ResponseHandler implements Serializable {

    private List<Slot> slots;

    public SlotSearchResponse(Page page, List<Slot> slots) {
        super(page);
        this.slots = slots;
    }

    public SlotSearchResponse(List<Slot> slots) {
        this.slots = slots;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
