package com.vaibhav.parkingReservation.controllers;

import com.vaibhav.parkingReservation.DTOs.SlotDTO;
import com.vaibhav.parkingReservation.DTOs.SlotSearchRequest;
import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;
import com.vaibhav.parkingReservation.DTOs.SlotTypeSearchRequest;
import com.vaibhav.parkingReservation.entity.Slot;
import com.vaibhav.parkingReservation.entity.SlotType;
import com.vaibhav.parkingReservation.response.SlotSearchResponse;
import com.vaibhav.parkingReservation.response.SlotTypeSearchResponse;
import com.vaibhav.parkingReservation.services.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "slot")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @GetMapping(path = "/search")
    public ResponseEntity<SlotSearchResponse> getAllSlots(@RequestParam(required = false, defaultValue = "1") final Integer pageNo,
                                                          @RequestParam(required = false, defaultValue = "20") final Integer pageSize, SlotSearchRequest slotSearchRequest) {
        return new ResponseEntity<>(slotService.getAllSlots(slotSearchRequest, pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping  (path = "/type/search")
    public ResponseEntity<SlotTypeSearchResponse> getAllSlotTypes(@RequestParam(required = false, defaultValue = "1") final Integer pageNo,
                                                                  @RequestParam(required = false, defaultValue = "20") final Integer pageSize, SlotTypeSearchRequest slotTypeSearchRequest) {
        return new ResponseEntity<>(slotService.getAllSlotTypes(slotTypeSearchRequest,pageNo,pageSize), HttpStatus.OK);
    }


    @PostMapping(path = "/design")
    public ResponseEntity<Map<String,Object>> designSlot(@RequestBody List<SlotTypeDTO> slotTypes) {
        return new ResponseEntity<>(slotService.addSlotTypes(slotTypes), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Map<String,Object>> addSlot(@RequestBody List<SlotDTO> slots) {
        return new ResponseEntity<>(slotService.addSlots(slots), HttpStatus.OK);
    }

//    @PostMapping(path = "/search")
//    public ResponseEntity<Map<String,Object>> searchSlot(@RequestBody List<SlotTypeDTO> slotTypes) {
//        return new ResponseEntity<>(slotService.addSlotTypes(slotTypes), HttpStatus.OK);
//    }
}
