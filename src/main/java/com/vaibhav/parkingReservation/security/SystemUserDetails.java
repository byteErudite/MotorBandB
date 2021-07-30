package com.vaibhav.parkingReservation.security;

import com.vaibhav.parkingReservation.constants.constantEntity.AssignedRole;
import com.vaibhav.parkingReservation.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SystemUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isActive;
    private List<GrantedAuthority> authorities;

    public SystemUserDetails(User user, List<AssignedRole> roles) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isActive  = user.isActive();
        this.authorities = roles.stream().map(String::valueOf).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public SystemUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
