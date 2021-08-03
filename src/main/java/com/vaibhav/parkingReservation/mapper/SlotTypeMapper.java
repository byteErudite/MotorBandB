package com.vaibhav.parkingReservation.mapper;

import com.vaibhav.parkingReservation.DTOs.SlotTypeDTO;
import com.vaibhav.parkingReservation.entity.SlotType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { HibernateAwareMapper.class})
public interface SlotTypeMapper {

    SlotType slotTypeDTOToSlotType(SlotTypeDTO slotTypeDTO);
}
