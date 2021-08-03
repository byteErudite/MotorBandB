package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.DTOs.SlotDTO;
import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;
import com.vaibhav.parkingReservation.entity.ParkingGarage;
import com.vaibhav.parkingReservation.entity.Slot;
import com.vaibhav.parkingReservation.entity.SlotType;
import com.vaibhav.parkingReservation.mapper.SlotMapper;
import com.vaibhav.parkingReservation.mapper.SlotTypeMapper;
import com.vaibhav.parkingReservation.repositories.ParkingGarageRepository;
import com.vaibhav.parkingReservation.repositories.SlotRepository;
import com.vaibhav.parkingReservation.repositories.SlotTypeRepository;
import com.vaibhav.parkingReservation.services.SlotService;
import com.vaibhav.parkingReservation.utilities.CommonUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class SlotServiceImpl implements SlotService {
    @Autowired
    SlotRepository slotRepository;

    @Autowired
    SlotTypeMapper slotTypeMapper;

    @Autowired
    SlotTypeRepository slotTypeRepository;

    @Autowired
    ParkingGarageRepository parkingGarageRepository;

    @Autowired
    SlotMapper slotMapper;

    @Override
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    @Transactional
    public Map<String,Object> addSlotTypes(List<SlotTypeDTO> slotTypes) {
        Map<String,Object> processedSlotTypes = new HashMap<>();
        slotTypes.stream().forEach(slotTypeDTO -> {
            try {
                saveSlotType(slotTypeDTO);
            } catch (Exception e) {
                processedSlotTypes.put("Failed", slotTypeDTO.toString());
                e.printStackTrace();
            }
        });
        if (CollectionUtils.isEmpty(processedSlotTypes)) {
            processedSlotTypes.put("Success","all slot types");
        }
        return processedSlotTypes;
    }

    @Override
    @Transactional
    public Map<String, Object> addSlots(List<SlotDTO> slots) {
        Map<String,Object> processedSlots = new HashMap<>();
        slots.stream().forEach(slot -> {
            try {
                saveSlot(slot);
            } catch (Exception e) {
                processedSlots.put("Failed", slot.toString());
                e.printStackTrace();
            }
        });
        return processedSlots;
    }

    private Slot saveSlot(SlotDTO slotDTO) throws Exception {
        if (CommonUtilities.isEmpty(slotDTO.getParkingGarageId()) || CommonUtilities.isEmpty(slotDTO.getSlotTypeId())) {
            throw new Exception("Parking garage id or slotType id cannot be null or empty");
        }
        Slot slot = slotMapper.slotDTOToSlot(slotDTO);
        setGarageAndSlotType(slot, slotDTO);
        return slotRepository.save(slot);
    }

    private void setGarageAndSlotType(Slot slot, SlotDTO slotDTO) throws Exception {
        Optional<ParkingGarage> parkingGarage = parkingGarageRepository.findById(UUID.fromString(slotDTO.getParkingGarageId()));
        Optional<SlotType> slotType = slotTypeRepository.findById(UUID.fromString(slotDTO.getSlotTypeId()));

        if (!parkingGarage.isPresent()) {
            throw new Exception("Invalid parking garage Id");
        }
        if (!slotType.isPresent()) {
            throw new Exception("Invalid slot type Id");
        }
        slot.setParkingGarage(parkingGarage.get());
        slot.setSlotType(slotType.get());
    }

    private SlotType saveSlotType(SlotTypeDTO slotTypeDTO) throws Exception {
        if (Objects.isNull(slotTypeDTO.getParkingGarageId())) {
            throw new Exception("parkingGarageId missing in request");
        }
        Optional<ParkingGarage> parkingGarage = parkingGarageRepository.findById(UUID.fromString(slotTypeDTO.getParkingGarageId()));
        if (!parkingGarage.isPresent()) {
            throw new Exception("Invalid parkingGarageId");
        }
        SlotType slotType = slotTypeMapper.slotTypeDTOToSlotType(slotTypeDTO);
        slotType.setParkingGarage(parkingGarage.get());
        return   slotTypeRepository.save(slotType);
    }
}
