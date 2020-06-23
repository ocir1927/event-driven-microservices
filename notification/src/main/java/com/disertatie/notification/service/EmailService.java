package com.disertatie.notification.service;

import com.costin.disertatie.api.event.UserAccountCreatedEvent;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @EventHandler
    @DisallowReplay
    void sendEmail(UserAccountCreatedEvent event) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("oncioiu.costi@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Mail: " + event.email +
                "Hello " +event.username +" \n This is your new account");
        System.out.println("Mail to: " + event.email);
        javaMailSender.send(msg);
    }

/*
    public void sendSignUpEmail(RoUser user, String generatedPass, Locale language) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("username", user.getUsername());
        params.put("password", generatedPass);

        final String emailSubject = templateWriter.generateSubjectText("sign-up", language, params);
        final String emailBody = templateWriter.generateBodyText("sign-up", language, params);
        sendMessage(user.getEmail(), emailSubject, emailBody, false, null, false);
    }
*/

}
