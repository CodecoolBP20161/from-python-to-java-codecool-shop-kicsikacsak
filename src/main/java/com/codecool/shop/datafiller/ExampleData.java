package com.codecool.shop.datafiller;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;


/**
 * Created by doramedgyasszay on 2016. 11. 16..
 */
public class ExampleData {

    public static void populateData(){

        Factory factory = new Factory();
        //setting up a new supplier
        Supplier amazon = factory.getInstance().newSupplier("Amazon", "Digital content and services");
        Supplier lenovo = factory.getInstance().newSupplier("Lenovo", "Computers");
        Supplier elemnt = factory.getInstance().newSupplier("Elemnt", "Luxury cases for electronic devices");
        Supplier apple = factory.getInstance().newSupplier("Apple", "Industry leader in smartphones");
        Supplier oneplus = factory.getInstance().newSupplier("OnePlus", "");

        //setting up a new product category
        ProductCategory tablet = factory.getInstance().productCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory mobile = factory.getInstance().productCategory("Smartphone", "Mobile Devices", "A freakin mobile phone.");
        ProductCategory cases = factory.getInstance().productCategory("Case", "Other", "Cases and covers for your devices.");

        //setting up products and printing it
        factory.getInstance().newProduct("Amazon Fire", 49, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        factory.getInstance().newProduct("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        factory.getInstance().newProduct("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon);
        factory.getInstance().newProduct("OnePlus 3", 439, "USD", "Dash Charge is faster, safer, cooler - and simply better - than any other fast charging technology available today.", mobile, oneplus);
        factory.getInstance().newProduct("Elemnt Python MacBook Case", 150, "USD", "Genuine snake skin fused to a polycarbonate snap on cover. The cover is easily removable and interchangeable.", cases, elemnt);
        factory.getInstance().newProduct("Elemnt Python iPad Skin", 100, "USD", "Genuine snake skin backed by a 3M Pressure Sensitive adhesive.", cases, elemnt);
        factory.getInstance().newProduct("Apple iPhone 7", 749, "USD", "iPhone 7 dramatically improves the most important aspects of the iPhone experience.", mobile, apple);
        factory.getInstance().newProduct("Lenovo Phab2", 199.9f, "USD", "Experience true immersive multimedia with the stunning 6.4inch HD screen and incredible Dolby Atmos® audio.", mobile, lenovo);

    }

}
