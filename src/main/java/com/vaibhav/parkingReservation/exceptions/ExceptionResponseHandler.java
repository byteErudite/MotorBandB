package com.vaibhav.parkingReservation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<CustomException> handleAllExceptions(CustomException exception, WebRequest req) {
        return new ResponseEntity<>(exception, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CustomException> handleAllExceptions(Exception exception, WebRequest req) {
        return new ResponseEntity<>(new CustomException(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, exception.getCause()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
