package com.vaibhav.parkingReservation.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.StringJoiner;


@JsonIgnoreProperties("stackTrace")
public class CustomException extends RuntimeException implements Serializable {
    private String message;
    private HttpStatus httpStatus;
    private Date timestamp;
    private String reason;

    @Override
    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }

    public CustomException setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public CustomException setMessage(String message) {
        this.message = message;
        return this;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public CustomException setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = Date.from(Instant.now());
    }

    public CustomException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = Date.from(Instant.now());
    }

    protected CustomException() {}

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomException.class.getSimpleName() + "[", "]")
                .add("message='" + message + "'")
                .add("httpStatus=" + httpStatus)
                .add("timestamp=" + timestamp)
                .add("reason='" + reason + "'")
                .toString();
    }
}
