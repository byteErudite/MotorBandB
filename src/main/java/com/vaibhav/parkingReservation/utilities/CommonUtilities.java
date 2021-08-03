package com.vaibhav.parkingReservation.utilities;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static com.vaibhav.parkingReservation.constants.Constants.EMPTY_STRING;

public class CommonUtilities {

    public static Date getDateFromString(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean isEmpty(String string) {
        return Objects.isNull(string) || EMPTY_STRING.equals(string);
    }
}
