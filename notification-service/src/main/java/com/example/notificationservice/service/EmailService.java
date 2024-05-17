package com.example.notificationservice.service;

import com.example.notificationservice.model.User;
import com.example.notificationservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;


@Component
@RequiredArgsConstructor
@Log4j2
public class EmailService {


    private final JavaMailSender emailSender;
    private final UserRepo userRepo;


    public void sentMessage(String status, Integer id) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(System.getenv("FROM"));
        mailMessage.setTo(getUserEmail(id));
        mailMessage.setSubject("Delivery status");
        mailMessage.setText(String.format("Your delivery status is %s", status));
        emailSender.send(mailMessage);
        log.info("Message was sent to user with id: {}", id);
    }

    private String getUserEmail(Integer id) {
        User user = userRepo.findUserById(id).orElseThrow(NoSuchElementException::new);
        return user.getEmail();
    }
}
