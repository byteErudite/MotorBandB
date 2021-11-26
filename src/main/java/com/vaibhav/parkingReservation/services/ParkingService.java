package com.vaibhav.parkingReservation.services;


import com.vaibhav.parkingReservation.DTOs.ParkingGarageDTO;
import com.vaibhav.parkingReservation.entity.ParkingGarage;
import com.vaibhav.parkingReservation.requests.GarageSearchRequest;
import com.vaibhav.parkingReservation.response.GarageSearchResponse;

public interface ParkingService {

    ParkingGarage createGarage(ParkingGarageDTO parkingGarageDTO) throws Exception;
    GarageSearchResponse searchGarage(GarageSearchRequest garageSearchRequest, int pageNumber, int size);

}
