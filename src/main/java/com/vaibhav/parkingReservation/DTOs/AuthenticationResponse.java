package com.vaibhav.parkingReservation.DTOs;

public class AuthenticationResponse {
    private String jwtToken;

    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthenticationResponse{");
        sb.append("jwtToken='").append(jwtToken).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
