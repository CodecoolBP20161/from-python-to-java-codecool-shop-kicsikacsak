package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by svindler on 16.11.2016.
 */


public class Cart {

    private HashMap<Product, Integer> products = new HashMap<>();

    public Cart(){}

    public Integer allProducts() {
        Integer sum = 0;
        for (Integer item : this.products.values()) {
            sum += item;
        }
        return sum;
    }

    public void add(Product product) {

        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);

        } else {
            products.put(product, 1);
        }
    }


}
