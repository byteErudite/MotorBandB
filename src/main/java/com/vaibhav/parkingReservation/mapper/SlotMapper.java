package com.vaibhav.parkingReservation.mapper;

import com.vaibhav.parkingReservation.DTOs.SlotDTO;
import com.vaibhav.parkingReservation.entity.Slot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { HibernateAwareMapper.class})
public interface SlotMapper {

    Slot slotDTOToSlot(SlotDTO slot);
}
