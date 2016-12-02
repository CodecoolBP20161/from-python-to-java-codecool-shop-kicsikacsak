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

    public Cart() {
    }

    //turns back the sum of the products
    public Integer allProducts() {
        Integer sum = 0;
        for (Integer item : this.products.values()) {
            sum += item;
        }
        return sum;
    }

    //Adding a product to a hashmap, if the products is already a key, just ++ the quantity
    public void add(Product product) {


        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
            try {
                totalSum += product.getDefaultPrice();
            } catch (NullPointerException e){
                System.out.println("You try ");
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

    //return the product hashmap
    public HashMap getProducts() {
        return products;
    }

    //return the total sum of the product prices
    public Float getTotalSum() {

        try{
            return totalSum;
        } catch (NullPointerException e) {
            System.out.println("totalsum is null");
        }
        return 0.0f;
    }
}