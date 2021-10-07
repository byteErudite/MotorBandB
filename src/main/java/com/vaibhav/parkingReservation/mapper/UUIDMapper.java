package com.vaibhav.parkingReservation.mapper;

import java.util.UUID;

public class UUIDMapper {
    public static String convertUUIDToString(final UUID uuid) {
        if (uuid == null) {
            return null;
        }
        return uuid.toString();
    }

    public static UUID convertStringToUUID(final String uuid) {
        if (uuid == null) {
            return null;
        }
        return UUID.fromString(uuid);
    }

    private UUIDMapper() {
        super();
    }
}
