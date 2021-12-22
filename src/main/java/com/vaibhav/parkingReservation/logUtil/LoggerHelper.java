package com.vaibhav.parkingReservation.logUtil;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.vaibhav.parkingReservation.constants.Constants.CORRELATION_ID;
import static com.vaibhav.parkingReservation.constants.Constants.DIVIDER;
import static com.vaibhav.parkingReservation.constants.Constants.ERROR;
import static com.vaibhav.parkingReservation.constants.Constants.INFO;
import static com.vaibhav.parkingReservation.constants.Constants.DEBUG;
import static com.vaibhav.parkingReservation.constants.Constants.EMPTY_STRING;

@Component
public class LoggerHelper {
    private static final Logger logger = LogManager.getLogger(LoggerHelper.class);

    public void write(String logType, String header, String message, Exception e, String errorMessage) {
        if (INFO.equals(logType)) {
            logger.info(INFO + CORRELATION_ID + CurrentThread.correlationId() + DIVIDER + header + " -> " + message);
        } else if (ERROR.equals(logType)) {
            logger.info(ERROR + CORRELATION_ID + CurrentThread.correlationId() + DIVIDER + header + " -> " + message + (Objects.nonNull(e) ? e.toString() : EMPTY_STRING) + errorMessage);
        } else if (DEBUG.equals(logType)) {
            logger.info(DEBUG + CORRELATION_ID + CurrentThread.correlationId() + DIVIDER + header + " -> " + message + (Objects.nonNull(e) ? e.toString() : EMPTY_STRING) + errorMessage);
        }
    }
}
