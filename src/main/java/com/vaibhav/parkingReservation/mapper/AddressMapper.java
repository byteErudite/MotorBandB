package com.vaibhav.parkingReservation.mapper;

import com.vaibhav.parkingReservation.DTOs.AddressDTO;
import com.vaibhav.parkingReservation.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { HibernateAwareMapper.class})
public interface AddressMapper {

    Address AddressDTOToAddress(AddressDTO address);
}
