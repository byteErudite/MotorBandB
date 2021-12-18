package com.vaibhav.parkingReservation.logUtil;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.vaibhav.parkingReservation.constants.Constants.ERROR;
import static com.vaibhav.parkingReservation.constants.Constants.INFO;

@Component
public class LoggerHelper {
    private static final Logger logger = LogManager.getLogger(LoggerHelper.class);


    public void write(String logType, String header, String message,  Exception e, String errorMessage) {
        if (INFO.equals(logType)) {
            logger.info(INFO + " ------  correlationId : " + MDC.get("correlationId") + " -----" + header + " -> " + message );
        } else if (ERROR.equals(logType)) {
            logger.info(INFO + " ------  correlationId : " + MDC.get("correlationId") + " -----" + header + " -> " + message + (Objects.nonNull(e) ? e.toString() : "") + errorMessage);
        }
    }
}
