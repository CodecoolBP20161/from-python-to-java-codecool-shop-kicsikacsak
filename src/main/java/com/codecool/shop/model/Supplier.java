package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class Supplier extends BaseModel {
    private ArrayList<Product> products;

    private static AtomicInteger nextId = new AtomicInteger();


    public Supplier(String name, String description) {
        super(name);
        this.products = new ArrayList<>();
        setId(nextId.incrementAndGet());
    }

    public Supplier(Integer id, String name) {
        super(name);
        this.products = new ArrayList<>();
        this.id = id;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}