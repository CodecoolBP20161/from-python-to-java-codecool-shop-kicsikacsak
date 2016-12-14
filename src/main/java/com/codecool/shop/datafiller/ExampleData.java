package com.codecool.shop.datafiller;

import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;


/**
 * Created by doramedgyasszay on 2016. 11. 16..
 */


public class ExampleData {

    public static void populateData(){

        //setting up a new supplier
        Supplier amazon = Factory.getInstance().newSupplier("Amazon", "Digital content and services");
        Supplier lenovo = Factory.getInstance().newSupplier("Lenovo", "Computers");
        Supplier elemnt = Factory.getInstance().newSupplier("Elemnt", "Luxury cases for electronic devices");
        Supplier apple = Factory.getInstance().newSupplier("Apple", "Industry leader in smartphones");
        Supplier oneplus = Factory.getInstance().newSupplier("OnePlus", "");

        //setting up a new product category
        ProductCategory tablet = Factory.getInstance().productCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory mobile = Factory.getInstance().productCategory("Smartphone", "Mobile Devices", "A freakin mobile phone.");
        ProductCategory cases = Factory.getInstance().productCategory("Case", "Other", "Cases and covers for your devices.");

        //setting up products and printing it
        Factory.getInstance().newProduct("Amazon Fire", 49, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        Factory.getInstance().newProduct("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        Factory.getInstance().newProduct("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon);
        Factory.getInstance().newProduct("OnePlus 3", 439, "USD", "Dash Charge is faster, safer, cooler - and simply better - than any other fast charging technology available today.", mobile, oneplus);
        Factory.getInstance().newProduct("Elemnt Python MacBook Case", 150, "USD", "Genuine snake skin fused to a polycarbonate snap on cover. The cover is easily removable and interchangeable.", cases, elemnt);
        Factory.getInstance().newProduct("Elemnt Python iPad Skin", 100, "USD", "Genuine snake skin backed by a 3M Pressure Sensitive adhesive.", cases, elemnt);
        Factory.getInstance().newProduct("Apple iPhone 7", 749, "USD", "iPhone 7 dramatically improves the most important aspects of the iPhone experience.", mobile, apple);
        Factory.getInstance().newProduct("Lenovo Phab2", 199.9f, "USD", "Experience true immersive multimedia with the stunning 6.4inch HD screen and incredible Dolby Atmos® audio.", tablet, lenovo);

    }

}
