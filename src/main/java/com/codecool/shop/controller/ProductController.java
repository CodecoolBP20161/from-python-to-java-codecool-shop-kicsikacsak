package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {


    private static void eventHandler(SupplierDao supplierDataStore, ProductCategoryDao productCategoryDataStore, Map params, Request req) {
        params.put("allcategories", productCategoryDataStore.getAll());
        params.put("allsuppliers", supplierDataStore.getAll());

        Integer cartlength = 0;
        try {
            Cart cart = req.session().attribute("cart");
            cartlength = cart.allProducts();
        } catch (NullPointerException e) {

        }
        params.put("cartlength", cartlength);


    }


    private static ProductDao getProductDao() {

        return ProductDaoMem.getInstance();
    }

    private static ProductCategoryDao getProductCategoryDao() {

        return ProductCategoryDaoMem.getInstance();
    }

    private  static SupplierDao getSupplierDao() {

        return SupplierDaoMem.getInstance();
    }


    public static ModelAndView renderProducts(Request req, Response res) {

        Map params = new HashMap<>();


        params.put("category", getProductCategoryDao().find(1));
        params.put("products", getProductDao().getAll());
        eventHandler(getSupplierDao(), getProductCategoryDao(), params, req);

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsByCategory(Request request, Response response) {

        Map params = new HashMap<>();

        //filter by the request id.
        params.put("category", getProductCategoryDao().find(Integer.parseInt(request.params(":id"))));
        params.put("products", getProductDao().getBy(getProductCategoryDao().find(Integer.parseInt(request.params(":id")))));
        eventHandler(getSupplierDao(), getProductCategoryDao(), params, request);


        return new ModelAndView(params, "product/index");

    }

    public static ModelAndView renderProductsBySupplier(Request request, Response response) {

        Map params = new HashMap<>();

        //filter by the request id.
        params.put("category", getSupplierDao().find(Integer.parseInt(request.params(":id"))));
        params.put("products", getProductDao().getBy(getSupplierDao().find(Integer.parseInt(request.params(":id")))));
        eventHandler(getSupplierDao(), getProductCategoryDao(), params, request);

        return new ModelAndView(params, "product/index");

    }

}
