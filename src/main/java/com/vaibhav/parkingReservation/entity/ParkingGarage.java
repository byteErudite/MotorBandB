package com.vaibhav.parkingReservation.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "parking_garage")
public class ParkingGarage {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID parkingGarageId;
    private String garageName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    private boolean isOperational;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy="parkingGarage")
    private Set<Slot> slots;

    @OneToMany(mappedBy="parkingGarage")
    private Set<SlotType> slotTypes;

    public UUID getParkingGarageId() {
        return parkingGarageId;
    }

    public void setParkingGarageId(UUID parkingGarageId) {
        this.parkingGarageId = parkingGarageId;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public void setOperational(boolean operational) {
        isOperational = operational;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Slot> getSlots() {
        return slots;
    }

    public void setSlots(Set<Slot> slots) {
        this.slots = slots;
    }

    public Set<SlotType> getSlotTypes() {
        return slotTypes;
    }

    public void setSlotTypes(Set<SlotType> slotTypes) {
        this.slotTypes = slotTypes;
    }

    public ParkingGarage(UUID parkingGarageId, String garageName, Address address, boolean isOperational, User user) {
        this.parkingGarageId = parkingGarageId;
        this.garageName = garageName;
        this.address = address;
        this.isOperational = isOperational;
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ParkingGarage{");
        sb.append("parkingGarageId=").append(parkingGarageId);
        sb.append(", garageName='").append(garageName).append('\'');
        sb.append(", address=").append(address);
        sb.append(", isOperational=").append(isOperational);
        sb.append(", user=").append(user);
        sb.append(", slots=").append(slots);
        sb.append(", slotTypes=").append(slotTypes);
        sb.append('}');
        return sb.toString();
    }
}
