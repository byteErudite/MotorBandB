package com.vaibhav.parkingReservation.services;

import com.vaibhav.parkingReservation.DTOs.ParkingGarageDTO;
import com.vaibhav.parkingReservation.entity.ParkingGarage;

public interface ParkingService {

    ParkingGarage createGarage(ParkingGarageDTO parkingGarageDTO) throws Exception;
}
