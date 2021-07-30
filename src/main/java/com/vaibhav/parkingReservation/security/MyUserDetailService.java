package com.vaibhav.parkingReservation.security;

import com.vaibhav.parkingReservation.constants.constantEntity.AssignedRole;
import com.vaibhav.parkingReservation.entity.Role;
import com.vaibhav.parkingReservation.entity.User;
import com.vaibhav.parkingReservation.repositories.RoleRepository;
import com.vaibhav.parkingReservation.repositories.UserRepository;
import com.vaibhav.parkingReservation.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Invalid username : " + username);
        }
        List<UUID> roleIds = userRoleRepository.getUserRoleIdbyUserId(user.getUserId());
        List<AssignedRole> roles = roleRepository.findAllById(roleIds).stream().map(Role::getRole).collect(Collectors.toList());
        return new SystemUserDetails(user, roles);
    }
}
