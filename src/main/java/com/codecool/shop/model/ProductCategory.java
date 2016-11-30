package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductCategory extends BaseModel {
    private String department;
    private ArrayList<Product> products;

    private static AtomicInteger nextId = new AtomicInteger();


    public ProductCategory(String name, String department, String description) {
        super(name);
        this.department = department;
        this.description = description;
        this.products = new ArrayList<>();
        setId(nextId.incrementAndGet());
    }

    public ProductCategory(Integer id, String name, String department, String description) {
        super(name);
        this.department = department;
        this.description = description;
        this.products = new ArrayList<>();
        this.id = id;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() { return description;}

    public void setDescription(String description) { this.description = description;}

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
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}