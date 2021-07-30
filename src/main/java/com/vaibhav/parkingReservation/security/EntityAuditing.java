package com.vaibhav.parkingReservation.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class EntityAuditing implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Object user =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SystemUserDetails currentUser = (SystemUserDetails) (user);
        return Optional.of(currentUser.getUsername());
    }
}
