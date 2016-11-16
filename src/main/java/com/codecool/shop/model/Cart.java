package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svindler on 16.11.2016.
 */


public class Cart {

    private static List<Product> productids = new ArrayList<>();

    public Cart(){}

    public static List getList() {
        return productids;
    }

    public void add(Product product) {
        productids.add(product);
    }


}
