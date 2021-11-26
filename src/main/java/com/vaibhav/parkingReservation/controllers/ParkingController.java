package com.vaibhav.parkingReservation.controllers;

import com.vaibhav.parkingReservation.DTOs.ParkingGarageDTO;
import com.vaibhav.parkingReservation.entity.ParkingGarage;
import com.vaibhav.parkingReservation.requests.GarageSearchRequest;
import com.vaibhav.parkingReservation.response.GarageSearchResponse;
import com.vaibhav.parkingReservation.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @PostMapping(path = "/create")
    public ResponseEntity<ParkingGarage> designSlot(@RequestBody ParkingGarageDTO garage) throws Exception {
        return new ResponseEntity<>(parkingService.createGarage(garage), HttpStatus.OK);
    }

    @PostMapping(path = "/search")
    public ResponseEntity<GarageSearchResponse> designSlot(@RequestBody GarageSearchRequest garageSearchRequest, @RequestParam(required = false, defaultValue = "1") final Integer pageNo,
                                                           @RequestParam(required = false, defaultValue = "20") final Integer pageSize) {
        return new ResponseEntity<>(parkingService.searchGarage(garageSearchRequest, pageNo, pageSize), HttpStatus.OK);
    }
}
