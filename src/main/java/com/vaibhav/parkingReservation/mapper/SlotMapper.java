package com.vaibhav.parkingReservation.mapper;

import com.vaibhav.parkingReservation.DTOs.SlotDTO;
import com.vaibhav.parkingReservation.entity.Slot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { HibernateAwareMapper.class, UUIDMapper.class})
public interface SlotMapper {

    @Mapping(ignore = true, target = "startDate")
    @Mapping(ignore = true, target = "endDate")
    Slot slotDTOToSlot(SlotDTO slot);
}
