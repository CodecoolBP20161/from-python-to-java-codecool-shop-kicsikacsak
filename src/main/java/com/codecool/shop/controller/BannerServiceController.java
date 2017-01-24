package com.codecool.shop.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by mz on 2017.01.23..
 */
public class BannerServiceController {

    private static final String SERVICE_URL = "http://localhost:60000";

    public String getBanner() throws URISyntaxException, IOException {
        StringEntity jsonstring = new StringEntity("{}");

        return Request.Post(SERVICE_URL + "/banner").body(jsonstring)
                .execute().returnContent().asString();
    }

    public String getBannerByUsernameAndCart() throws URISyntaxException, IOException {
        StringEntity jsonstring = new StringEntity("{user:user, apikey:1234, cart: [{name:iphone, category:mobile, defaultprice:100USD, quantity:1}, {name:ledtv, category:tv, defaultprice:200USD, quantity:2}]}}");

        return Request.Post(SERVICE_URL + "/banner").body(jsonstring)
                .execute().returnContent().asString();
    }
}
