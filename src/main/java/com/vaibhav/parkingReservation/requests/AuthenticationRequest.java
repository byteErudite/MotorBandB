package com.vaibhav.parkingReservation.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthenticationRequest {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String firstname;

    @NotNull
    @NotEmpty
    private String lastName;

    private String dateOfBirth;

    public AuthenticationRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public AuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthenticationRequest{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", dateOfBirth='").append(dateOfBirth).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
