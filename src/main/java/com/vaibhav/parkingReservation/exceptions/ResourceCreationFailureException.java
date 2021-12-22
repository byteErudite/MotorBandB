package com.vaibhav.parkingReservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.Instant;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class ResourceCreationFailureException extends CustomException {
    public ResourceCreationFailureException(String message) {
        super(message, HttpStatus.EXPECTATION_FAILED);
    }
}
