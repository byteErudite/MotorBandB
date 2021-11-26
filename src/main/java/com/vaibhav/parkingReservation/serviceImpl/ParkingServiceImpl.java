package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.DTOs.AddressDTO;
import com.vaibhav.parkingReservation.DTOs.ParkingGarageDTO;
import com.vaibhav.parkingReservation.customRepositories.GarageCustomRepository;
import com.vaibhav.parkingReservation.entity.Address;
import com.vaibhav.parkingReservation.entity.ParkingGarage;
import com.vaibhav.parkingReservation.entity.User;
import com.vaibhav.parkingReservation.exceptions.BadRequestException;
import com.vaibhav.parkingReservation.exceptions.ResourceCreationFailureException;
import com.vaibhav.parkingReservation.mapper.AddressMapper;
import com.vaibhav.parkingReservation.mapper.ParkingGarageMapper;
import com.vaibhav.parkingReservation.repositories.AddressRepository;
import com.vaibhav.parkingReservation.repositories.ParkingGarageRepository;
import com.vaibhav.parkingReservation.repositories.UserRepository;
import com.vaibhav.parkingReservation.requests.GarageSearchRequest;
import com.vaibhav.parkingReservation.response.GarageSearchResponse;
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

    @Autowired
    GarageCustomRepository garageCustomRepository;

    @Override
    @Transactional
    public ParkingGarage createGarage(ParkingGarageDTO parkingGarageDTO) {
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
            throw new ResourceCreationFailureException("Garage addition failed, please try again");
        }
    }

    public GarageSearchResponse searchGarage(GarageSearchRequest garageSearchRequest, int pageNumber, int size) {
        return garageCustomRepository.searchGarage(garageSearchRequest, pageNumber, size);
    }

    private void validateRequest(User user, ParkingGarageDTO parkingGarageDTO) {
        if (Objects.nonNull(user) && !user.getUserId().equals(parkingGarageDTO.getUserId())) {
            throw new BadRequestException("Illegal operation, cannot create garage for other user");
        }
        if (Objects.isNull(parkingGarageDTO.getUserId())) {
            throw new BadRequestException("Invalid user ID");
        }
    }

    private Address addAddress(AddressDTO address) {
        if (CommonUtilities.isEmpty(address.getPinCode()) || CommonUtilities.isEmpty(address.getCity())
                || CommonUtilities.isEmpty(address.getCity()) || CommonUtilities.isEmpty(address.getCountry())) {
            throw new BadRequestException("Address fields cannot be null or empty");
        }
        return addressRepository.save(addressMapper.AddressDTOToAddress(address));
    }
}
