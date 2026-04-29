package com.user.service.consumer;

import com.user.service.dto.NotificationDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "library-notifications", groupId = "library-group")
    public void consumeNotification(NotificationDto notification) {
        System.out.println("Received notification for user " + notification.getUserId()
                + " about book " + notification.getBookId()
                + ": " + notification.getMessage());
    }
}