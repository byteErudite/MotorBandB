package com.vaibhav.parkingReservation.services;

import com.vaibhav.parkingReservation.DTOs.BookingRequest;
import com.vaibhav.parkingReservation.entity.Booking;

public interface BookingService {
    Booking createBooking(BookingRequest bookingRequest) throws Exception;
}
