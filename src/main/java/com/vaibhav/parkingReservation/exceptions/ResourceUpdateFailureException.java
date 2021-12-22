package com.vaibhav.parkingReservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ResourceUpdateFailureException extends CustomException {
    public ResourceUpdateFailureException(String message) {
        super(message, HttpStatus.EXPECTATION_FAILED);
    }
}
