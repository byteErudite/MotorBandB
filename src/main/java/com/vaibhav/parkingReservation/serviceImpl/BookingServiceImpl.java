package com.vaibhav.parkingReservation.serviceImpl;

import com.vaibhav.parkingReservation.DTOs.BookingDTO;
import com.vaibhav.parkingReservation.logUtil.LoggerHelper;
import com.vaibhav.parkingReservation.requests.BookingRequest;
import com.vaibhav.parkingReservation.constants.constantEntity.BookingStatus;
import com.vaibhav.parkingReservation.constants.constantEntity.PaymentStatus;
import com.vaibhav.parkingReservation.entity.Booking;
import com.vaibhav.parkingReservation.entity.Slot;
import com.vaibhav.parkingReservation.entity.SlotAvailability;
import com.vaibhav.parkingReservation.entity.User;
import com.vaibhav.parkingReservation.exceptions.BadRequestException;
import com.vaibhav.parkingReservation.mapper.BookingMapper;
import com.vaibhav.parkingReservation.repositories.BookingRepository;
import com.vaibhav.parkingReservation.repositories.SlotAvailabilityRepository;
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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.vaibhav.parkingReservation.constants.Errors.INVALID_SLOT;
import static com.vaibhav.parkingReservation.constants.Errors.INVALID_SLOT_AVAILABILITY;
import static com.vaibhav.parkingReservation.constants.Errors.INVALID_TIME_RANGE;
import static com.vaibhav.parkingReservation.constants.Errors.INVALID_USER;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    SlotRepository slotRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SlotAvailabilityRepository slotAvailabilityRepository;

    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    LoggerHelper loggerHelper;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingRequest bookingRequest) {
        Optional<Slot> slot = slotRepository.findById(UUID.fromString(bookingRequest.getSlotId()));
        if (!slot.isPresent()) {
            throw new BadRequestException(INVALID_SLOT);
        }

        Optional<User> user = userRepository.findById(UUID.fromString(bookingRequest.getUserId()));
        if (!user.isPresent()) {
            throw new BadRequestException(INVALID_USER);
        }
        SlotAvailability slotAvailability = bookingRequest.getSlotAvailability();
        if (Objects.isNull(slotAvailability)) {
            throw new BadRequestException(INVALID_SLOT_AVAILABILITY);
        }
        Date startDateTime = CommonUtilities.getDateTimeFromString(bookingRequest.getStartDateTime());
        Date endDateTime = CommonUtilities.getDateTimeFromString(bookingRequest.getEndDateTime());

        if (startDateTime.after(slotAvailability.getStartTime()) || endDateTime.before(slotAvailability.getEndTime())) {
            throw new BadRequestException(INVALID_TIME_RANGE);
        }

        splitSlotAvailability(slotAvailability, new Timestamp(startDateTime.getTime()), new Timestamp(endDateTime.getTime()));
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

    private void splitSlotAvailability(SlotAvailability originalSlotlotAvailability, Timestamp bookingStartDateTime, Timestamp bookingEndDateTime) {
        List<SlotAvailability> updatedAvailabilities = new ArrayList<>();

        SlotAvailability slotAvailability1 = originalSlotlotAvailability.copyObject();
        SlotAvailability slotAvailability2 = originalSlotlotAvailability.copyObject();

        slotAvailability1.setEndTime(bookingStartDateTime);
        updatedAvailabilities.add(slotAvailability1);


        slotAvailability2.setStartTime(bookingEndDateTime);
        updatedAvailabilities.add(slotAvailability2);

        originalSlotlotAvailability.setIsDeleted(true);
        updatedAvailabilities.add(originalSlotlotAvailability);

        slotAvailabilityRepository.saveAll(updatedAvailabilities);
    }
}
