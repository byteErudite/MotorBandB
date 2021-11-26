package com.vaibhav.parkingReservation.mapper;

import com.vaibhav.parkingReservation.DTOs.UserDTO;
import com.vaibhav.parkingReservation.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { HibernateAwareMapper.class, UUIDMapper.class})
public interface UserMapper {

    UserDTO userToUserDTO(User user);
}
