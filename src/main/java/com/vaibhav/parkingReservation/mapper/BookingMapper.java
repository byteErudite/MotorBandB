package com.vaibhav.parkingReservation.mapper;

import com.vaibhav.parkingReservation.DTOs.BookingDTO;
import com.vaibhav.parkingReservation.entity.Booking;
import com.vaibhav.parkingReservation.entity.Slot;
import com.vaibhav.parkingReservation.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = { HibernateAwareMapper.class})
public interface BookingMapper {

    @Mapping(source = "user", target = "userId", qualifiedByName = "userToUserId")
    @Mapping(source = "slot", target = "slotId", qualifiedByName = "slotToSlotId")
    BookingDTO BookingToBookingDTO(Booking booking);

    @Named("userToUserId")
    static UUID userToUserId(User user) {
        return user.getUserId();
    }

    @Named("slotToSlotId")
    static UUID slotToSlotId(Slot slot) {
        return slot.getSlotId();
    }
}
