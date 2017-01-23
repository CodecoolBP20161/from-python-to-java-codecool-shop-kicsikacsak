package com.codecool.shop.controller;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by svindler on 23.01.2017.
 */
public class VideoServiceController {

    private static final Logger logger = LoggerFactory.getLogger(VideoServiceController.class);
    private static final String SERVICE_URL = "http://localhost:60000";

    public String getVideoForProduct(String searchWord) throws URISyntaxException, IOException {
        return execute("/apivideos?search=" + searchWord);
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
