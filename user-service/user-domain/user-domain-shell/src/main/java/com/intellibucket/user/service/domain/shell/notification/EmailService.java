package com.intellibucket.user.service.domain.shell.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;

    public void sendRegisteringMessage(String toUserEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toUserEmail);
        message.setSubject("Welcome to our platform! ");
        message.setText("Hello " + username + "," +
                "\n\nThank you for registering with us! We are thrilled to have you onboard. \n" +
                "Feel free to explore all features and services we offer.\n" +
                "We look forward to being a part of your journey.\n\n" +
                "Best regards, \n\n" +
                "The Orizon Team.");
        emailSender.send(message);
    }
}