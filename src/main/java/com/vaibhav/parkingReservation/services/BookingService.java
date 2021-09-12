package com.vaibhav.parkingReservation.services;

import com.vaibhav.parkingReservation.DTOs.BookingDTO;
import com.vaibhav.parkingReservation.DTOs.BookingRequest;
import com.vaibhav.parkingReservation.entity.Booking;

public interface BookingService {
    BookingDTO createBooking(BookingRequest bookingRequest) throws Exception;
}
