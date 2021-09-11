package com.vaibhav.parkingReservation.utilities;


import com.sun.istack.Nullable;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
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

    public static Date getDateTimeFromString(String date) {
        try {
            //String testDate = "29-Apr-2010,13:00:14 PM";
            DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
            return formatter.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean isEmpty(String string) {
        return Objects.isNull(string) || EMPTY_STRING.equals(string);
    }

    public static boolean isNotEmpty(@Nullable Collection<?> collectionObject) {
        return !CollectionUtils.isEmpty(collectionObject);
    }
}
