package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.DTOs.AuthenticationRequest;
import com.vaibhav.parkingReservation.constants.constantEntity.AssignedRole;
import com.vaibhav.parkingReservation.entity.Role;
import com.vaibhav.parkingReservation.entity.User;
import com.vaibhav.parkingReservation.entity.UserRole;
import com.vaibhav.parkingReservation.repositories.RoleRepository;
import com.vaibhav.parkingReservation.repositories.UserRepository;
import com.vaibhav.parkingReservation.repositories.UserRoleRepository;
import com.vaibhav.parkingReservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public String registerUser(AuthenticationRequest registrationRequest, AssignedRole role) throws Exception {
        validateRegistrationRequest(registrationRequest);
        User user = null;
        try {
            user = userRepository.save(new User(registrationRequest.getUsername(), registrationRequest.getPassword(), true, new Date(System.currentTimeMillis()), registrationRequest.getEmail()));
            Role dbRole = roleRepository.findByRoleName(role);
            userRoleRepository.save(new UserRole(dbRole.getRoleId(), user.getUserId()));
            return "success";
        } catch (Exception e) {
            throw new Exception("User creation failed please try again");
        }
    }

    private void validateRegistrationRequest(AuthenticationRequest registrationRequest) throws Exception {
        if (registrationRequest.getUsername().equals(null) || registrationRequest.getPassword().equals(null)) {
            throw new Exception("username or password cannot be empty or null");
        }
    }


}
