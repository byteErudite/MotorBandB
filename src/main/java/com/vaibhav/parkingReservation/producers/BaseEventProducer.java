package com.vaibhav.parkingReservation.producers;

import com.vaibhav.parkingReservation.logUtil.LoggerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.vaibhav.parkingReservation.constants.Constants.ERROR;
import static com.vaibhav.parkingReservation.constants.Constants.INFO;

@Component
public class BaseEventProducer {

    @Autowired
    private LoggerHelper loggerHelper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public <T> void produceEvent(T payload, String description, String correlationId, String topicName) {
        KafkaEntity kafkaEntity = new KafkaEntity(correlationId, topicName, description, payload.toString());
        produceEvent(topicName, kafkaEntity);
    }

    private void produceEvent(String topicName, KafkaEntity kafkaEntity) {
        try {
            kafkaTemplate.send(topicName, kafkaEntity.toString());
            loggerHelper.write(INFO, "NOTIFICATION SUCCESSFULLY TO SENT TO KAFKA : notification -> {} : ", kafkaEntity.toString(), null, null);

        } catch (Exception e) {
            loggerHelper.write(ERROR, "NOTIFICATION FAILED TO SEND TO KAFKA : notification -> {} : ", kafkaEntity.toString(), e, e.getMessage());
        }
    }
}
