package com.vaibhav.parkingReservation.response;

import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;

import java.io.Serializable;
import java.util.List;

public class SlotTypeSearchResponse extends ResponseHandler implements Serializable {

    private List<SlotTypeDTO> slotTypes;

    public SlotTypeSearchResponse(Page page, List<SlotTypeDTO> slots) {
        super(page);
        this.slotTypes = slots;
    }

    public List<SlotTypeDTO> getSlotTypes() {
        return slotTypes;
    }

    public void setSlotTypes(List<SlotTypeDTO> slotTypes) {
        this.slotTypes = slotTypes;
    }
}
