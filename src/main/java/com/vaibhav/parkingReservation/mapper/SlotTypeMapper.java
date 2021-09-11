package com.vaibhav.parkingReservation.mapper;

import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;
import com.vaibhav.parkingReservation.entity.SlotType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { HibernateAwareMapper.class})
public interface SlotTypeMapper {

    SlotType slotTypeDTOToSlotType(SlotTypeDTO slotTypeDTO);

    List<SlotType> slotTypeDTOToSlotType(List<SlotTypeDTO> slotTypeDTOS);
}
