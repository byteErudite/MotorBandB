package com.vaibhav.parkingReservation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user" , schema = "public")
@NamedEntityGraph(name = "simpleUser", attributeNodes = {})
public class User implements Serializable {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID userId;

    private String username;
    private String password;
    private boolean isActive;
    private Timestamp addedDate;
    private String email;
    private String name;
    private Date dateOfBirth;

    @OneToOne(mappedBy = "user")
    private ParkingGarage parkingGarage;

    @OneToOne(mappedBy = "user")
    private Booking booking;

    @OneToOne(mappedBy = "user")
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    public User() {
    }

    public User(String username, String password, boolean isActive, Timestamp addedDate, String email, String name, Date dateOfBirth) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.addedDate = addedDate;
        this.email = email;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public ParkingGarage getParkingGarage() {
        return parkingGarage;
    }

    public void setParkingGarage(ParkingGarage parkingGarage) {
        this.parkingGarage = parkingGarage;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Timestamp addedDate) {
        this.addedDate = addedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", addedDate=").append(addedDate);
        sb.append(", email='").append(email).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append(", parkingGarage=").append(parkingGarage);
        sb.append(", booking=").append(booking);
        sb.append('}');
        return sb.toString();
    }
}
