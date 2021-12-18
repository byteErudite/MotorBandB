package com.vaibhav.parkingReservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SortParamUnavailableException extends RuntimeException {
    public SortParamUnavailableException(String message) {
        super(message);
    }

    public SortParamUnavailableException(String message, Throwable cause) {
        super(message,cause);
    }
}
