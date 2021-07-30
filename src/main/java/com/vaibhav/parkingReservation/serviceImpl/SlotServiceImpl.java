package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.entity.Slot;
import com.vaibhav.parkingReservation.repositories.SlotRepository;
import com.vaibhav.parkingReservation.services.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotServiceImpl implements SlotService {
    @Autowired
    SlotRepository slotRepository;

    @Override
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }
}
