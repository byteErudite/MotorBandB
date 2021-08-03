package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.DTOs.AddressDTO;
import com.vaibhav.parkingReservation.DTOs.ParkingGarageDTO;
import com.vaibhav.parkingReservation.entity.Address;
import com.vaibhav.parkingReservation.entity.ParkingGarage;
import com.vaibhav.parkingReservation.entity.User;
import com.vaibhav.parkingReservation.mapper.AddressMapper;
import com.vaibhav.parkingReservation.mapper.ParkingGarageMapper;
import com.vaibhav.parkingReservation.repositories.AddressRepository;
import com.vaibhav.parkingReservation.repositories.ParkingGarageRepository;
import com.vaibhav.parkingReservation.repositories.UserRepository;
import com.vaibhav.parkingReservation.security.SystemUserDetails;
import com.vaibhav.parkingReservation.services.ParkingService;
import com.vaibhav.parkingReservation.utilities.CommonUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    ParkingGarageRepository parkingGarageRepository;

    @Autowired
    ParkingGarageMapper parkingGarageMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMapper addressMapper;

    @Override
    @Transactional
    public ParkingGarage createGarage(ParkingGarageDTO parkingGarageDTO) throws Exception {
        SystemUserDetails owner = (SystemUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(owner.getUsername());
        validateRequest(user, parkingGarageDTO);
        try {
            addAddress(parkingGarageDTO.getAddress());
            ParkingGarage parkingGarage = parkingGarageMapper.parkingGarageDTOToParkingGarage(parkingGarageDTO);
            parkingGarage.setUser(user);
            return parkingGarageRepository.save(parkingGarage);
        } catch (Exception e) {
            System.out.println("Garage addition failed");
            throw new Exception("Garage addition failed, please try again");
        }
    }

    private void validateRequest(User user, ParkingGarageDTO parkingGarageDTO) throws Exception {
        if (Objects.nonNull(user) && !user.getUserId().equals(parkingGarageDTO.getUserId())) {
            throw new Exception("Illegal operation, cannot create garage for other user");
        }
        if (Objects.isNull(parkingGarageDTO.getUserId())) {
            throw new Exception("Invalid user ID");
        }
    }

    private Address addAddress(AddressDTO address) throws Exception {
        if (CommonUtilities.isEmpty(address.getPinCode()) || CommonUtilities.isEmpty(address.getCity())
                || CommonUtilities.isEmpty(address.getCity()) || CommonUtilities.isEmpty(address.getCountry())) {
            throw new Exception("Address fields cannot be null or empty");
        }
        return addressRepository.save(addressMapper.AddressDTOToAddress(address));
    }
}
