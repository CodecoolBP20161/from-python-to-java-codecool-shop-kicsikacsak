package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();


        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getAll());
        params.put("allcategories", productCategoryDataStore.getAll());
        params.put("allsuppliers", supplierDataStore.getAll());
        System.out.println(supplierDataStore.getAll());

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsByCategory(Request request, Response response) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();

        //filter by the request id.
        params.put("category", productCategoryDataStore.find(Integer.parseInt(request.params(":id"))));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(request.params(":id")))));
        params.put("allsuppliers", supplierDataStore.getAll());
        params.put("allcategories", productCategoryDataStore.getAll());

        return new ModelAndView(params, "product/index");

    }

    public static ModelAndView renderProductsBySupplier(Request request, Response response) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();


        Map params = new HashMap<>();

        //filter by the request id.
        params.put("category", supplierDataStore.find(Integer.parseInt(request.params(":id"))));
        params.put("products", productDataStore.getBy(supplierDataStore.find(Integer.parseInt(request.params(":id")))));
        params.put("allsuppliers", supplierDataStore.getAll());
        params.put("allcategories", productCategoryDataStore.getAll());

        return new ModelAndView(params, "product/index");

    }

}
