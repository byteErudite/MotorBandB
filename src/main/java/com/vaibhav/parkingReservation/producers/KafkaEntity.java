package com.vaibhav.parkingReservation.producers;


import java.io.Serializable;

public class KafkaEntity implements Serializable {

    private String correlationId;
    private String header;
    private String description;
    private String payload;

    public KafkaEntity() {
    }

    public KafkaEntity(String correlationId, String header, String description, String payload) {
        this.correlationId = correlationId;
        this.header = header;
        this.description = description;
        this.payload = payload.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("KafkaEntity{");
        sb.append("correlationId='").append(correlationId).append('\'');
        sb.append(", header='").append(header).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", payload='").append(payload).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
