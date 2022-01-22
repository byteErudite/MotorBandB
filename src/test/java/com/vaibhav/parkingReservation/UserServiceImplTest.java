package com.vaibhav.parkingReservation;


import com.vaibhav.parkingReservation.constants.constantEntity.AssignedRole;
import com.vaibhav.parkingReservation.entity.User;
import com.vaibhav.parkingReservation.exceptions.ResourceCreationFailureException;
import com.vaibhav.parkingReservation.exceptions.ResourceNotFoundException;
import com.vaibhav.parkingReservation.repositories.UserRepository;
import com.vaibhav.parkingReservation.requests.AuthenticationRequest;
import com.vaibhav.parkingReservation.serviceImpl.UserServiceImpl;
import com.vaibhav.parkingReservation.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.vaibhav.parkingReservation.constants.Constants.AND;
import static com.vaibhav.parkingReservation.constants.Constants.EMAIL_ALREADY_IN_USE;
import static com.vaibhav.parkingReservation.constants.Constants.USERNAME_ALREADY_IN_USE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;



//    @BeforeAll
//    public static void setup() {
//        ReflectionTestUtils.setField(SecurityContextHolder.getContext().getAuthentication(), "",null);
//    }

//    @Test
//    public void testCurrentUser() {
//        try (MockedStatic mocked = mockStatic(SecurityContextHolder.class)) {
//            mocked.when(() -> SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(null);
//            assertNotNull(Assertions.assertThrows(ResourceNotFoundException.class, () -> {
//                userService.findCurrentUser();
//            }));
//        }
//
//    }

    private AuthenticationRequest getRegistrationRequest(String username, String email) {
        AuthenticationRequest registrationRequest = new AuthenticationRequest();
        registrationRequest.setUsername(username);
        registrationRequest.setEmail(email);
        return registrationRequest;
    }

    private User getUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        return user;
    }

    @Test
    public void testRegisterUser_emailAndUsernameExist() {
        final String USERNAME = "test_user";
        final String EMAIL = "test@gmail.com";
        ResourceCreationFailureException exception = Assertions.assertThrows(ResourceCreationFailureException.class, () -> {
            Mockito.when(userRepository.findByUsernameOrEmail(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(List.of(getUser(USERNAME, EMAIL)));
            userService.registerUser(getRegistrationRequest(USERNAME, EMAIL), AssignedRole.USER);
        });
        assertEquals(EMAIL_ALREADY_IN_USE + AND + USERNAME_ALREADY_IN_USE, exception.getMessage());
    }

    @Test
    public void testRegisterUser_usernameExist() {
        final String USERNAME = "test_user";
        final String EMAIL_1 = "test1@gmail.com";
        final String EMAIL_2 = "test2@gmail.com";
        ResourceCreationFailureException exception = Assertions.assertThrows(ResourceCreationFailureException.class, () -> {
            Mockito.when(userRepository.findByUsernameOrEmail(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(List.of(getUser(USERNAME, EMAIL_1)));
            userService.registerUser(getRegistrationRequest(USERNAME, EMAIL_2), AssignedRole.USER);
        });
        assertEquals(USERNAME_ALREADY_IN_USE, exception.getMessage());
    }

    @Test
    public void testRegisterUser_emailExist() {
        final String USERNAME_1 = "test_user1";
        final String USERNAME_2 = "test_user2";
        final String EMAIL = "test@gmail.com";
        ResourceCreationFailureException exception = Assertions.assertThrows(ResourceCreationFailureException.class, () -> {
            Mockito.when(userRepository.findByUsernameOrEmail(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(List.of(getUser(USERNAME_1, EMAIL)));
            userService.registerUser(getRegistrationRequest(USERNAME_2, EMAIL), AssignedRole.USER);
        });
        assertEquals(EMAIL_ALREADY_IN_USE, exception.getMessage());
    }

}
