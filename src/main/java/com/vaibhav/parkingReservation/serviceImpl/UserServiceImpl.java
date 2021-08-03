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
import com.vaibhav.parkingReservation.utilities.CommonUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;


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
        try {
            User user = userRepository.save(populateUserFromRegistrationRequest(registrationRequest));
            Role dbRole = roleRepository.findByRoleName(role);
            userRoleRepository.save(new UserRole(user, dbRole));
            return "success";
        } catch (Exception e) {
            throw new Exception("User creation failed please try again");
        }
    }

    private void validateRegistrationRequest(AuthenticationRequest registrationRequest) throws Exception {
        checkIfUsernameOrEmailExists(registrationRequest);
    }

    private void checkIfUsernameOrEmailExists(AuthenticationRequest registrationRequest) throws Exception {
        List<User> existingDuplicateUsers = userRepository.findByUsernameOrEmail(registrationRequest.getUsername(), registrationRequest.getEmail());
        if (!existingDuplicateUsers.isEmpty()) {
            boolean usernameExists = false;
            boolean emailExists = false ;
            for (User user : existingDuplicateUsers) {
                if (user.getUsername().equals(registrationRequest.getUsername())) {
                    usernameExists = true;
                }
                if (user.getEmail().equals(registrationRequest.getEmail())) {
                    emailExists = true;
                }
            }
            String duplicateException = null;
            if (usernameExists && emailExists) {
                duplicateException = "email and username already in use";
            } else if (usernameExists) {
                duplicateException = "username already in use";
            } else if (emailExists) {
                duplicateException = "email already in use";
            }

            if (Objects.nonNull(duplicateException)) {
                throw new Exception(duplicateException);
            }
        }

    }

    private User populateUserFromRegistrationRequest(AuthenticationRequest registrationRequest) throws Exception {
        Date dateOfBirth = CommonUtilities.getDateFromString(registrationRequest.getDateOfBirth());
        if (Objects.isNull(dateOfBirth)) {
            throw new Exception("Please provide date in dd/mm/yyyy format");
        }
        return new User(registrationRequest.getUsername(), registrationRequest.getPassword(), true, new Timestamp(System.currentTimeMillis()), registrationRequest.getEmail(), registrationRequest.getName(), dateOfBirth);
    }
}
