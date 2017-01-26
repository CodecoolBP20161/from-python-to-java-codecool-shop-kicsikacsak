package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by mz on 2017.01.23..
 */
public class BannerServiceController {

    private static final String SERVICE_URL = "http://localhost:60001";

    public String getBanner() throws URISyntaxException, IOException {
        StringEntity jsonstring = new StringEntity("{}");

        return Request.Post(SERVICE_URL + "/banner").body(jsonstring)
                .execute().returnContent().asString();
    }


    public JSONObject getBannerByUsernameAndCart(JSONObject jsonObject) throws URISyntaxException, IOException, NullPointerException {
        StringEntity jsonstring = new StringEntity(jsonObject.toString());
        String resp = Request.Post(SERVICE_URL + "/banner").body(jsonstring)
                .execute().returnContent().toString();
        return new JSONObject(resp);

    }
}
