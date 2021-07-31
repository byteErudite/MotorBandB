package com.vaibhav.parkingReservation.controllers;

import com.vaibhav.parkingReservation.DTOs.AuthenticationRequest;
import com.vaibhav.parkingReservation.DTOs.AuthenticationResponse;
import com.vaibhav.parkingReservation.constants.constantEntity.AssignedRole;
import com.vaibhav.parkingReservation.security.MyUserDetailService;
import com.vaibhav.parkingReservation.security.SystemUserDetails;
import com.vaibhav.parkingReservation.services.UserService;
import com.vaibhav.parkingReservation.utilities.JwtUTil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    UserService userService;

    @Autowired
    private JwtUTil jwtUTil;

    @RequestMapping(value = "authenticate")
    public ResponseEntity<?> authenticateAndReturnJwtToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Bad credentials");
        }
        final UserDetails userDetails =  userDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwtToken = jwtUTil.generateToken(userDetails);
        return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
    }

    @PostMapping(value = "/registerUser")
     public ResponseEntity<String> registerUser(@RequestBody AuthenticationRequest registrationRequest) throws Exception {
         return new ResponseEntity<>(userService.registerUser(registrationRequest, AssignedRole.USER), HttpStatus.OK);
     }

    @PostMapping(value = "/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody AuthenticationRequest registrationRequest) throws Exception {
        return new ResponseEntity<>(userService.registerUser(registrationRequest, AssignedRole.ADMIN), HttpStatus.OK);
    }
}
