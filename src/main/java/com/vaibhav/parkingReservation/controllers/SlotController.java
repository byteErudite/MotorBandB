package com.vaibhav.parkingReservation.controllers;

import com.vaibhav.parkingReservation.entity.Slot;
import com.vaibhav.parkingReservation.services.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "slot")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Slot>> getAllSlots() {
        return new ResponseEntity<>(slotService.getAllSlots(), HttpStatus.OK);
    }
}
