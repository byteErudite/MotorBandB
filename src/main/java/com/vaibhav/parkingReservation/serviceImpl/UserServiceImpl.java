package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.exceptions.ResourceCreationFailureException;
import com.vaibhav.parkingReservation.exceptions.ResourceNotFoundException;
import com.vaibhav.parkingReservation.logUtil.LoggerHelper;
import com.vaibhav.parkingReservation.requests.AuthenticationRequest;
import com.vaibhav.parkingReservation.DTOs.UserDTO;
import com.vaibhav.parkingReservation.constants.constantEntity.AssignedRole;
import com.vaibhav.parkingReservation.entity.Role;
import com.vaibhav.parkingReservation.entity.User;
import com.vaibhav.parkingReservation.entity.UserRole;
import com.vaibhav.parkingReservation.mapper.UserMapper;
import com.vaibhav.parkingReservation.repositories.RoleRepository;
import com.vaibhav.parkingReservation.repositories.UserRepository;
import com.vaibhav.parkingReservation.repositories.UserRoleRepository;
import com.vaibhav.parkingReservation.security.SystemUserDetails;
import com.vaibhav.parkingReservation.services.UserService;
import com.vaibhav.parkingReservation.utilities.CommonUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.vaibhav.parkingReservation.constants.Constants.AND;
import static com.vaibhav.parkingReservation.constants.Constants.EMAIL_ALREADY_IN_USE;
import static com.vaibhav.parkingReservation.constants.Constants.ERROR;
import static com.vaibhav.parkingReservation.constants.Constants.USERNAME_ALREADY_IN_USE;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserMapper userMapper;

    LoggerHelper logger;

    @Override
    @Transactional
    public String registerUser(AuthenticationRequest registrationRequest, AssignedRole role) throws Exception {
        validateRegistrationRequest(registrationRequest);
        try {
            User user = userRepository.save(populateUserFromRegistrationRequest(registrationRequest));
            Role dbRole = roleRepository.findByRoleName(role);
            if (Objects.isNull(dbRole)) {
                throw new Exception("Role does not exists");
            }
            userRoleRepository.save(new UserRole(user, dbRole));
            return "success";
        } catch (ResourceCreationFailureException e){
            logger.write(ERROR, "USER CREATION FAILED : request -> {} : ", registrationRequest.toString(),e,e.getMessage());
            throw e;
        }catch (Exception e) {
            logger.write(ERROR, "USER CREATION FAILED : request -> {} : ", registrationRequest.toString(),e,e.getMessage());
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
                duplicateException = EMAIL_ALREADY_IN_USE + AND + USERNAME_ALREADY_IN_USE;
            } else if (usernameExists) {
                duplicateException = USERNAME_ALREADY_IN_USE;
            } else if (emailExists) {
                duplicateException = EMAIL_ALREADY_IN_USE;
            }
            if (Objects.nonNull(duplicateException)) {
                throw new ResourceCreationFailureException(duplicateException);
            }
        }
    }

    private User populateUserFromRegistrationRequest(AuthenticationRequest registrationRequest) throws Exception {
        Date dateOfBirth = CommonUtilities.getDateFromString(registrationRequest.getDateOfBirth());
        if (Objects.isNull(dateOfBirth)) {
            throw new Exception("Please provide date in dd/mm/yyyy format");
        }
        return new User(registrationRequest.getUsername(), registrationRequest.getPassword(), true, new Timestamp(System.currentTimeMillis()), registrationRequest.getEmail(), registrationRequest.getFirstName(), registrationRequest.getLastName(), dateOfBirth);
    }

    public UserDTO findCurrentUser() {
        SystemUserDetails systemUserDetails = (SystemUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.isNull(systemUserDetails)) {
            throw new ResourceNotFoundException("Failed to find user details");
        }
        String username = systemUserDetails.getUsername();
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new ResourceNotFoundException("Failed to find user details");
        }
        return userMapper.userToUserDTO(user);
    }
}
