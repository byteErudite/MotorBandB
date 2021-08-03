package com.vaibhav.parkingReservation.repositories;

import com.vaibhav.parkingReservation.entity.SlotType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SlotTypeRepository extends JpaRepository<SlotType, UUID> {


}
