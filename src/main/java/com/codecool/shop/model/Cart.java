package com.codecool.shop.model;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;

/**
 * Created by svindler on 16.11.2016.
 */


public class Cart {

    private HashMap<Product, Integer> products = new HashMap<>();
    public Float totalSum = 0.0f;
    private String anyad = "anyad";

    public Cart() {
    }

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
            try {
                totalSum += product.getDefaultPrice();
            } catch (NullPointerException e){
                System.out.println("some error");
            }


        } else {
            products.put(product, 1);
            try {
                totalSum += product.getDefaultPrice();
            } catch (NullPointerException e){
                System.out.println("some error");

            }

        }
    }

    public HashMap getProducts() {
        return products;
    }

    public Float getTotalSum() {

        try{
            return totalSum;
        } catch (NullPointerException e) {
            System.out.println("totalsum is null");
        }
        return 0.0f;
    }
}