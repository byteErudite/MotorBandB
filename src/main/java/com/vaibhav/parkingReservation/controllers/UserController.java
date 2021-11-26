package com.vaibhav.parkingReservation.controllers;

import com.vaibhav.parkingReservation.DTOs.UserDTO;
import com.vaibhav.parkingReservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/details")
    public ResponseEntity<UserDTO> getAllSlots() {
        return new ResponseEntity<>(userService.findCurrentUser(), HttpStatus.OK);
    }
}
