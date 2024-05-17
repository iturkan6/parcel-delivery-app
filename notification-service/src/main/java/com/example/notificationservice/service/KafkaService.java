package com.example.notificationservice.service;

import com.example.notificationservice.model.KafkaModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class KafkaService {
    private final EmailService emailService;
    @KafkaListener(id = "listenGroupFoo", topics = "user_status")
    public KafkaModel listenGroupFoo(KafkaModel model) {
        emailService.sentMessage(model.status(), model.id());
        log.info("Got data from broker for user with id: {}", model.id());
        return model;
    }
}
