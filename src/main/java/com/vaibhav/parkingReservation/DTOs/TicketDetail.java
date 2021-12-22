package com.vaibhav.parkingReservation.DTOs;

import com.vaibhav.parkingReservation.constants.constantEntity.BookingStatus;
import com.vaibhav.parkingReservation.constants.constantEntity.PaymentStatus;

import java.sql.Timestamp;

public class TicketDetail {
    private String  firstName;
    private String lastName;
    private Timestamp slotStartTime;
    private Timestamp slotEndTime;
    private PaymentStatus paymentStatus;
    private BookingStatus bookingStatus;
    private Timestamp printedAt;
    private String GarageName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pinCode;

    public TicketDetail(String firstName, String lastName, Timestamp slotStartTime, Timestamp slotEndTime, PaymentStatus paymentStatus, BookingStatus bookingStatus, Timestamp printedAt, String garageName, String addressLine1, String addressLine2, String city, String state, String country, String pinCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.slotStartTime = slotStartTime;
        this.slotEndTime = slotEndTime;
        this.paymentStatus = paymentStatus;
        this.bookingStatus = bookingStatus;
        this.printedAt = printedAt;
        GarageName = garageName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(Timestamp slotStartTime) {
        this.slotStartTime = slotStartTime;
    }

    public Timestamp getSlotEndTime() {
        return slotEndTime;
    }

    public void setSlotEndTime(Timestamp slotEndTime) {
        this.slotEndTime = slotEndTime;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Timestamp getPrintedAt() {
        return printedAt;
    }

    public void setPrintedAt(Timestamp printedAt) {
        this.printedAt = printedAt;
    }

    public String getGarageName() {
        return GarageName;
    }

    public void setGarageName(String garageName) {
        GarageName = garageName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TicketDetail{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", slotStartTime=").append(slotStartTime);
        sb.append(", slotEndTime=").append(slotEndTime);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append(", bookingStatus=").append(bookingStatus);
        sb.append(", printedAt=").append(printedAt);
        sb.append(", GarageName='").append(GarageName).append('\'');
        sb.append(", addressLine1='").append(addressLine1).append('\'');
        sb.append(", addressLine2='").append(addressLine2).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", pinCode='").append(pinCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}