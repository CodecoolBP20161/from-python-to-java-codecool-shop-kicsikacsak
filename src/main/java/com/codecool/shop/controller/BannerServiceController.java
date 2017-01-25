package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
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


    public String getBannerByUsernameAndCart(User user, Cart cart) throws URISyntaxException, IOException, NullPointerException {
        try{
            StringEntity jsonstring = new StringEntity("{user:" + user.getUsername() + ", apikey:" + user.getPassword()+
                    "cart: [{name:" + cart.getProducts() +
                    ", category:" + cart.getProducts() +
                    ", defaultprice:" + cart.getProducts() +
                    ", quantity:" + cart.getProducts().values() + "}}");
            return Request.Post(SERVICE_URL + "/banner").body(jsonstring)
                    .execute().returnContent().asString();

        } catch(NullPointerException e){}



        return "";
    }
}
