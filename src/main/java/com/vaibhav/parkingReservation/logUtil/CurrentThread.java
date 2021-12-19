package com.vaibhav.parkingReservation.logUtil;

public class CurrentThread {

    private static final InheritableThreadLocal<String> CORRELATION_ID = new InheritableThreadLocal<>();

    public static String correlationId() {
        return CORRELATION_ID.get();
    }

    public static void setCorrelationId(String value) {
        CORRELATION_ID.set(value);
    }

    public static void removeCorrelationId() {
        CORRELATION_ID.remove();
    }

    private CurrentThread() {
        super();
    }

}
