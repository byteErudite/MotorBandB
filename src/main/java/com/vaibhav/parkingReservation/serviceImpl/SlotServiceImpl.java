package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.DTOs.SlotDTO;
import com.vaibhav.parkingReservation.requests.SlotSearchRequest;
import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;
import com.vaibhav.parkingReservation.requests.SlotTypeSearchRequest;
import com.vaibhav.parkingReservation.customRepositories.SlotCustomRepository;
import com.vaibhav.parkingReservation.entity.ParkingGarage;
import com.vaibhav.parkingReservation.entity.Slot;
import com.vaibhav.parkingReservation.entity.SlotAvailability;
import com.vaibhav.parkingReservation.entity.SlotType;
import com.vaibhav.parkingReservation.exceptions.BadRequestException;
import com.vaibhav.parkingReservation.mapper.SlotMapper;
import com.vaibhav.parkingReservation.mapper.SlotTypeMapper;
import com.vaibhav.parkingReservation.repositories.ParkingGarageRepository;
import com.vaibhav.parkingReservation.repositories.SlotAvailabilityRepository;
import com.vaibhav.parkingReservation.repositories.SlotRepository;
import com.vaibhav.parkingReservation.repositories.SlotTypeRepository;
import com.vaibhav.parkingReservation.response.SlotAvailabilitySearchResponse;
import com.vaibhav.parkingReservation.response.SlotSearchResponse;
import com.vaibhav.parkingReservation.response.SlotTypeSearchResponse;
import com.vaibhav.parkingReservation.services.SlotService;
import com.vaibhav.parkingReservation.utilities.CommonUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.vaibhav.parkingReservation.constants.Constants.HYPHEN;

@Service
public class SlotServiceImpl implements SlotService {
    @Autowired
    SlotRepository slotRepository;

    @Autowired
    SlotTypeMapper slotTypeMapper;

    @Autowired
    SlotTypeRepository slotTypeRepository;

    @Autowired
    SlotCustomRepository slotCustomRepository;

    @Autowired
    ParkingGarageRepository parkingGarageRepository;

    @Autowired
    SlotAvailabilityRepository slotAvailabilityRepository;

    @Autowired
    SlotMapper slotMapper;

    @Override
    public SlotSearchResponse searchSlotTypes(SlotSearchRequest slotSearchRequest, int pageNumber, int pageSize) {
        return slotCustomRepository.getslots(slotSearchRequest, pageNumber, pageSize);
    }

    @Override
    public SlotAvailabilitySearchResponse searchSlotAvailability(SlotSearchRequest slotSearchRequest, int pageNumber, int pageSize) {
        return slotCustomRepository.searchSlotAvailability(slotSearchRequest, pageNumber, pageSize);
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

    public SlotTypeSearchResponse getAllSlotTypes(SlotTypeSearchRequest slotTypeSearchRequest, int pageNumber, int pageSize) {
        return slotCustomRepository.searchSlotTypes(slotTypeSearchRequest, pageNumber, pageSize);
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

    private Slot saveSlot(SlotDTO slotDTO) {
        if (CommonUtilities.isEmpty(slotDTO.getParkingGarageId()) || CommonUtilities.isEmpty(slotDTO.getSlotTypeId())) {
            throw new BadRequestException("Parking garage id or slotType id cannot be null or empty");
        }
        Slot slot = slotMapper.slotDTOToSlot(slotDTO);
        setTimeAndCode(slot, slotDTO);
        setGarageAndSlotType(slot, slotDTO);
        slot = slotRepository.save(slot);
        saveSlotAvailability(slot, slotDTO);
        return slot;
    }

    private void setTimeAndCode(Slot slot, SlotDTO slotDTO) {

        String slotCode = UUID.randomUUID().toString().replace("-","") + HYPHEN + 0;
        slot.setSlotCode(slotCode);
        slotDTO.setSlotCode(slotDTO.getIdentifier1().substring(0,5) + slotDTO.getIdentifier2().substring(0,5) + slotDTO.getIdentifier3().substring(0,5));
    }

    private void saveSlotAvailability(Slot slot, SlotDTO slotDTO) {
        SlotAvailability slotAvailability = new SlotAvailability();
        Date startDate = CommonUtilities.getDateFromString(slotDTO.getStartDate());
        if (Objects.isNull(startDate) || startDate.before(new Date(System.currentTimeMillis()))) {
            slotAvailability.setStartTime(new Timestamp(System.currentTimeMillis()));
        }
        slotAvailability.setEndTime(new Timestamp(CommonUtilities.getDateFromString("01/01/2050").getTime()));
        slot.setSlotAvailabilities(Arrays.asList(slotAvailability));
        slotAvailability.setSlotId(slot.getSlotId());
        slotAvailabilityRepository.save(slotAvailability);
    }

    private void setGarageAndSlotType(Slot slot, SlotDTO slotDTO) {
        Optional<ParkingGarage> parkingGarage = parkingGarageRepository.findById(UUID.fromString(slotDTO.getParkingGarageId()));
        Optional<SlotType> slotType = slotTypeRepository.findById(UUID.fromString(slotDTO.getSlotTypeId()));

        if (!parkingGarage.isPresent()) {
            throw new BadRequestException("Invalid parking garage Id");
        }
        if (!slotType.isPresent()) {
            throw new BadRequestException("Invalid slot type Id");
        }
        slot.setFunctional(true);
        slot.setParkingGarage(parkingGarage.get());
        slot.setSlotType(slotType.get());
    }

    private SlotType saveSlotType(SlotTypeDTO slotTypeDTO) {
        if (Objects.isNull(slotTypeDTO.getParkingGarageId())) {
            throw new BadRequestException("parkingGarageId missing in request");
        }
        Optional<ParkingGarage> parkingGarage = parkingGarageRepository.findById(slotTypeDTO.getParkingGarageId());
        if (!parkingGarage.isPresent()) {
            throw new BadRequestException("Invalid parkingGarageId");
        }
        SlotType slotType = slotTypeMapper.slotTypeDTOToSlotType(slotTypeDTO);
        slotType.setParkingGarage(parkingGarage.get());
        return   slotTypeRepository.save(slotType);
    }
}
