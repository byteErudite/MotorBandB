package com.vaibhav.parkingReservation.services;

import com.vaibhav.parkingReservation.DTOs.SlotDTO;
import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;
import com.vaibhav.parkingReservation.entity.Slot;

import java.util.List;
import java.util.Map;

public interface SlotService {

    List<Slot> getAllSlots();

    Map<String,Object> addSlotTypes(List<SlotTypeDTO> slotTypes);
    Map<String,Object> addSlots(List<SlotDTO> slots);

}
