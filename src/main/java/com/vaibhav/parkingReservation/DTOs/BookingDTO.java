package com.vaibhav.parkingReservation.DTOs;

import com.vaibhav.parkingReservation.constants.constantEntity.BookingStatus;
import com.vaibhav.parkingReservation.constants.constantEntity.PaymentStatus;
import com.vaibhav.parkingReservation.entity.BaseEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

public class BookingDTO extends BaseEntity implements Serializable {

    private UUID bookingId;
    private Timestamp startDate;
    private Timestamp endDate;
    private PaymentStatus paymentStatus;
    private BookingStatus bookingStatus;
    private UUID slotId;
    private UUID userId;

    public BookingDTO() {
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
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

    public UUID getSlotId() {
        return slotId;
    }

    public void setSlotId(UUID slotId) {
        this.slotId = slotId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookingDTO{");
        sb.append("bookingId=").append(bookingId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append(", bookingStatus=").append(bookingStatus);
        sb.append(", slotId=").append(slotId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
