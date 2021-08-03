package com.vaibhav.parkingReservation.mapper;

import com.vaibhav.parkingReservation.DTOs.ParkingGarageDTO;
import com.vaibhav.parkingReservation.entity.ParkingGarage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { HibernateAwareMapper.class})
public interface ParkingGarageMapper {

    public ParkingGarage parkingGarageDTOToParkingGarage(ParkingGarageDTO parkingGarageDTO);
}
