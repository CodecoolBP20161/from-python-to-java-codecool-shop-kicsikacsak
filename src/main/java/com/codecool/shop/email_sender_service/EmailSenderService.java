package com.codecool.shop.email_sender_service;

import com.codecool.shop.email_sender_service.EmailService.EmailSender;
import com.codecool.shop.email_sender_service.controller.EmailSenderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Created by svindler on 14.12.2016.
 */
public class EmailSenderService {
    private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

    private EmailSenderController controller;

    public static void main(String[] args) {
        logger.debug("Starting " + EmailSenderService.class.getName() + "...");

        setup(args);

        EmailSenderService application = new EmailSenderService();

        application.controller = new EmailSenderController(EmailSender.getInstance());

        // --- MAPPINGS ---
        get("/status", application.controller::status);
        get("/email", application.controller::emailSender);

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
            logger.error("Error while processing request", exception);
        });
    }

    private static void setup(String[] args){
        if(args == null || args.length == 0){
            logger.error("Port must be given as the first argument.");
            System.exit(-1);
        }

        try {
            port(Integer.parseInt(args[0]));
        } catch (NumberFormatException e){
            logger.error("Invalid port given '{}', it should be number.", args[0]);
            System.exit(-1);
        }
    }
}

