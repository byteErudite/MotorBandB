package com.vaibhav.parkingReservation.repositories;

import com.vaibhav.parkingReservation.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SlotRepository extends JpaRepository<Slot, UUID> {

}
