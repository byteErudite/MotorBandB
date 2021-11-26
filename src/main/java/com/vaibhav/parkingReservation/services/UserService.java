package com.vaibhav.parkingReservation.services;

import com.vaibhav.parkingReservation.requests.AuthenticationRequest;
import com.vaibhav.parkingReservation.DTOs.UserDTO;
import com.vaibhav.parkingReservation.constants.constantEntity.AssignedRole;

public interface UserService {

    String registerUser(AuthenticationRequest registrationRequest, AssignedRole role) throws Exception;
    UserDTO findCurrentUser();
}
