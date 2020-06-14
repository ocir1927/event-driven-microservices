package com.disertatie.notification.service;

import com.costin.disertatie.api.event.UserAccountCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class NotificationService {

    @EventHandler
    public void handle(UserAccountCreatedEvent event) {
        String to = event.email;
        String from = "stock.io@gmail.com";
        String host = "localhost";
        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);
        System.out.println("Email from " +  from + "| email to: " + to + " was sent");


//        Session session = Session.getDefaultInstance(properties);
//        try {
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//            // Set Subject: header field
//            message.setSubject("This is the Subject Line!");
//
//            // Now set the actual message
//            message.setText("This is actual message");
//
//            // Send message
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }

    }


}
