package com.vaibhav.parkingReservation.services;

import com.vaibhav.parkingReservation.DTOs.BookingDTO;
import com.vaibhav.parkingReservation.requests.BookingRequest;

public interface BookingService {
    BookingDTO createBooking(BookingRequest bookingRequest) throws Exception;
}
