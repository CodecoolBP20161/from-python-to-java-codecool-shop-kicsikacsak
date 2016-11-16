package com.codecool.shop.model;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;


/**
 * Created by doramedgyasszay on 2016. 11. 16..
 */
public class ExampleData {

    public static void populateData(){

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = Factory.newSupplier("Amazon", "Digital content and services");
        Supplier lenovo = Factory.newSupplier("Lenovo", "Computers");
        Supplier elemnt = Factory.newSupplier("Elemnt", "Luxury cases for electronic devices");
        Supplier apple = Factory.newSupplier("Apple", "Industry leader in smartphones");

        //setting up a new product category
        ProductCategory tablet = Factory.productCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory mobile = Factory.productCategory("Mobil", "Hardware", "A freakin mobile phone.");
        ProductCategory cases = Factory.productCategory("Cases", "Other", "Cases and covers for your devices.");

        //setting up products and printing it
        Factory.newProduct("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        Factory.newProduct("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        Factory.newProduct("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon);
        Factory.newProduct("Amazon Mobile", 49.9f, "HUF", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", mobile, amazon);
        Factory.newProduct("Elemnt Python MacBook Case", 150, "USD", "Genuine snake skin fused to a polycarbonate snap on cover. The cover is easily removable and interchangeable.", cases, elemnt);
        Factory.newProduct("Elemnt Python iPad Skin", 100, "USD", "Genuine snake skin backed by a 3M Pressure Sensitive adhesive.", cases, elemnt);
        Factory.newProduct("Apple iPhone 7", 749, "USD", "iPhone 7 dramatically improves the most important aspects of the iPhone experience.", mobile, apple);
        Factory.newProduct("Lenovo Vibe Z2 Pro", 439, "USD", "Take your photography and videos to the next level with the class-leading Lenovo VIBE Z2 Pro.", mobile, lenovo);

    }
}
