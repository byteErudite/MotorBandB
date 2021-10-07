package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.DTOs.BookingDTO;
import com.vaibhav.parkingReservation.DTOs.BookingRequest;
import com.vaibhav.parkingReservation.constants.constantEntity.BookingStatus;
import com.vaibhav.parkingReservation.constants.constantEntity.PaymentStatus;
import com.vaibhav.parkingReservation.entity.Booking;
import com.vaibhav.parkingReservation.entity.Slot;
import com.vaibhav.parkingReservation.entity.User;
import com.vaibhav.parkingReservation.exceptions.BadRequestException;
import com.vaibhav.parkingReservation.mapper.BookingMapper;
import com.vaibhav.parkingReservation.repositories.BookingRepository;
import com.vaibhav.parkingReservation.repositories.SlotRepository;
import com.vaibhav.parkingReservation.repositories.UserRepository;
import com.vaibhav.parkingReservation.services.BookingService;
import com.vaibhav.parkingReservation.utilities.CommonUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    SlotRepository slotRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingRequest bookingRequest) {
        Optional<Slot> slot = slotRepository.findById(UUID.fromString(bookingRequest.getSlotId()));
        if (!slot.isPresent()) {
            throw new BadRequestException("Invalid slot, please try with a valid slot");
        }
        Optional<User> user = userRepository.findById(UUID.fromString(bookingRequest.getUserId()));
        if (!user.isPresent()) {
            throw new BadRequestException("Invalid userId, please try with a valid user");
        }
        Date startDateTime = CommonUtilities.getDateTimeFromString(bookingRequest.getStartDateTime());
        Date endDateTime = CommonUtilities.getDateTimeFromString(bookingRequest.getEndDateTime());

        if (startDateTime.before(slot.get().getStartDate()) || endDateTime.after(slot.get().getEndDate())) {
            throw new BadRequestException("Time range selected in not inside slot");
        }

        splitSlots(slot.get(), new Timestamp(startDateTime.getTime()), new Timestamp(endDateTime.getTime()));
        Booking booking = new Booking();
        booking.setSlot(slot.get());
        booking.setUser(user.get());
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        booking.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        booking.setStartDate(new Timestamp(startDateTime.getTime()));
        booking.setEndDate(new Timestamp(endDateTime.getTime()));
        bookingRepository.save(booking);
        return bookingMapper.BookingToBookingDTO(booking);
    }

    private void setSplitSlotCode(Slot slot, String originalSlotCode) {
        int length = originalSlotCode.length();
        int slotCounter = Integer.valueOf(originalSlotCode.substring(length -1));
        slotCounter++;
        slot.setSlotCode(originalSlotCode.substring(0,length-2) + slotCounter);
    }

    private void splitSlots(Slot slot, Timestamp startDateTime, Timestamp endDateTime) {
        List<Slot> slotsToSave = new ArrayList<>();
        Slot startSlot = slot.copyObject();
        Slot endSlot = slot.copyObject();

        startSlot.setStartDate(slot.getStartDate());
        startSlot.setEndDate(startDateTime);
        setSplitSlotCode(startSlot, slot.getSlotCode());
        slotsToSave.add(startSlot);


        endSlot.setStartDate(endDateTime);
        endSlot.setEndDate(slot.getEndDate());
        setSplitSlotCode(endSlot, startSlot.getSlotCode());
        slotsToSave.add(endSlot);

        slot.setIsDeleted(true);
        slotsToSave.add(slot);

        slotRepository.saveAll(slotsToSave);
    }
}
