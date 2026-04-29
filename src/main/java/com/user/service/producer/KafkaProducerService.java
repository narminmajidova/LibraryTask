package com.user.service.producer;

import com.user.service.dto.NotificationDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, NotificationDto> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, NotificationDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(String topic, NotificationDto notification) {
        kafkaTemplate.send(topic, notification);
        System.out.println("Sent notification: " + notification.getMessage());
    }
}