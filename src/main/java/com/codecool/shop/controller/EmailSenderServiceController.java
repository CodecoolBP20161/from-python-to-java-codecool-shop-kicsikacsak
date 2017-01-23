package com.codecool.shop.controller;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by svindler on 14.12.2016.
 */

public class EmailSenderServiceController {
    private static final Logger logger = LoggerFactory.getLogger(EmailSenderServiceController.class);
    private static final String SERVICE_URL = "http://localhost:60005";

    public Boolean isRunning() throws URISyntaxException, IOException {
        logger.info("Checking E-mail SendingService status");

        Boolean running = execute("/status").equalsIgnoreCase("ok");

        if(running){
            logger.info("Email Fun Fact Service is running");
        } else {
            logger.warn("Email Service Service is not running");
        }

        return running;
    }

    public String sendMail(String to) throws URISyntaxException, IOException {
        return execute("/email?to=" + to);
    }

    private String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url).build();

        return org.apache.http.client.fluent.Request
                .Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}
