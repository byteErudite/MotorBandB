package com.vaibhav.parkingReservation.DTOs;

import com.vaibhav.parkingReservation.entity.BaseEntity;

import java.io.Serializable;
import java.util.UUID;

public class UserDTO implements Serializable {

    private UUID userId;
    private boolean isActive;
    private String name;

    public UserDTO() {
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append("userId=").append(userId);
        sb.append(", isActive=").append(isActive);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
