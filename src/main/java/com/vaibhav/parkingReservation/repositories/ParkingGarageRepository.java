package com.vaibhav.parkingReservation.repositories;

import com.vaibhav.parkingReservation.entity.ParkingGarage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingGarageRepository extends JpaRepository<ParkingGarage, UUID> {


}
