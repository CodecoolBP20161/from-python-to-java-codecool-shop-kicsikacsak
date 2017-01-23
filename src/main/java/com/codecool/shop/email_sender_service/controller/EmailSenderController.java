package com.codecool.shop.email_sender_service.controller;

import com.codecool.shop.email_sender_service.EmailService.EmailSender;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by svindler on 14.12.2016.
 */
public class EmailSenderController {
    private final EmailSender emailSender;
    public static final String emailTo = "to";



    public EmailSenderController(EmailSender emailSender){
        this.emailSender = emailSender;
    }

    public String emailSender(Request request, Response response) throws IOException, URISyntaxException {
        System.out.println(request.queryParams(emailTo) + "  ez az email amit megkapok");
        emailSender.sendMail(request.queryParams(emailTo));
        return "Email Sent !";
    }

    public String status(Request request, Response response) {
        return "ok";
    }
}
