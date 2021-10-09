package com.vaibhav.parkingReservation.repositories;

import com.vaibhav.parkingReservation.entity.SlotAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlotAvailabilityRepository extends JpaRepository<SlotAvailability, UUID> {
}
