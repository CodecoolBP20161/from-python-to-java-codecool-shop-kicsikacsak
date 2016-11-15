package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import com.sun.org.apache.xpath.internal.SourceTree;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();


        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getAll());
        params.put("allcategories", productCategoryDataStore.getAll());
        System.out.println(params);
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsByCategory(Request request, Response response) {
//        System.out.println("Ez az értéke a visszakapottnak " + request.params(":id"));

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();

        //filter by the request id.
        params.put("category", productCategoryDataStore.find(Integer.parseInt(request.params(":id"))));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(request.params(":id")))));
        params.put("allcategories", productCategoryDataStore.getAll());

        System.out.println(params);

        return new ModelAndView(params, "product/index");

    }

}
