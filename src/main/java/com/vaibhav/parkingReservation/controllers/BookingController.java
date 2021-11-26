package com.vaibhav.parkingReservation.controllers;

import com.vaibhav.parkingReservation.DTOs.BookingDTO;
import com.vaibhav.parkingReservation.requests.BookingRequest;
import com.vaibhav.parkingReservation.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping(path = "/create")
    public ResponseEntity<BookingDTO> bookSlot(@RequestBody BookingRequest bookingRequest) throws Exception {
        return new ResponseEntity<>(bookingService.createBooking(bookingRequest), HttpStatus.OK);
    }
}
