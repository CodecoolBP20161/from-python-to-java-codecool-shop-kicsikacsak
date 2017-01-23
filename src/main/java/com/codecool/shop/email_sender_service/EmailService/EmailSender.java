package com.codecool.shop.email_sender_service.EmailService;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class EmailSender {

    private static EmailSender INSTANCE;

    public static EmailSender getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EmailSender();
        }
        return INSTANCE;
    }

    public void sendMail(String to) throws URISyntaxException, IOException {
        // Recipient's email ID needs to be mentioned.

        // Sender's email ID needs to be mentioned
        final String username = "kicsikacsacodecool";//change accordingly
        final String password = "codecool";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "relay.jangosmtp.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("kicsikacsacodecool@gmail.com"));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Webshop Registration");

            // Now set the actual message
            message.setText("Welcome to the codecool webshop !!");

            // Send message
            try {
                Transport.send(message);
            } catch (Exception e) {
                System.out.println(e);
            }

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
