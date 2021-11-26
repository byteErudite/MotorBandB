package com.vaibhav.parkingReservation.services;

import com.vaibhav.parkingReservation.DTOs.SlotDTO;
import com.vaibhav.parkingReservation.requests.SlotSearchRequest;
import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;
import com.vaibhav.parkingReservation.requests.SlotTypeSearchRequest;
import com.vaibhav.parkingReservation.response.SlotAvailabilitySearchResponse;
import com.vaibhav.parkingReservation.response.SlotSearchResponse;
import com.vaibhav.parkingReservation.response.SlotTypeSearchResponse;

import java.util.List;
import java.util.Map;

public interface SlotService {

    SlotAvailabilitySearchResponse searchSlotAvailability(SlotSearchRequest slotSearchRequest, int pageNumber, int pageSize);
    SlotSearchResponse searchSlotTypes(SlotSearchRequest slotSearchRequest, int pageNumber, int pageSize);
    Map<String,Object> addSlotTypes(List<SlotTypeDTO> slotTypes);
    Map<String,Object> addSlots(List<SlotDTO> slots);
    SlotTypeSearchResponse getAllSlotTypes(SlotTypeSearchRequest slotTypeSearchRequest, int pageNumber, int pageSize);
}
