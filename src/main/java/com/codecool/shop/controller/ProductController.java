package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Cart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    //handle the basic rendering, and the session
    private static void eventHandler(SupplierDao supplierDataStore, ProductCategoryDao productCategoryDataStore, Map params, Request req) {
        params.put("allcategories", productCategoryDataStore.getAll());
        params.put("allsuppliers", supplierDataStore.getAll());

//        System.out.println(supplierDataStore.getAll());

        Integer cartlength = 0;
        HashMap cartContent = null;
        Float totalSum = 0.0f;
        try {
            Cart cart = req.session().attribute("cart");
            cartlength = cart.allProducts();
            cartContent = cart.getProducts();
            totalSum = cart.getTotalSum();

        } catch (NullPointerException e) {

        }
//        System.out.println(cartContent);
        params.put("cartlength", cartlength);
        params.put("cart", cartContent);
        params.put("totalsum", totalSum);

    }

    public static ModelAndView renderProducts(Request req, Response res) {

        Map params = new HashMap<>();

        DataStoreSwitcher switcher = new DataStoreSwitcher();

        params.put("category", switcher.getProductCategoryDao().find(1));
        params.put("products", switcher.getProductDao().getAll());
        eventHandler(switcher.getSupplierDao(), switcher.getProductCategoryDao(), params, req);
        params.put("allproducts", "All Products");

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsByCategory(Request request, Response response) {

        Map params = new HashMap<>();

        DataStoreSwitcher switcher = new DataStoreSwitcher();

        //filter by the request id.
        params.put("category", switcher.getProductCategoryDao().find(Integer.parseInt(request.params(":id"))));
        params.put("products", switcher.getProductDao().getBy(switcher.getProductCategoryDao().find(Integer.parseInt(request.params(":id")))));
        eventHandler(switcher.getSupplierDao(), switcher.getProductCategoryDao(), params, request);


        return new ModelAndView(params, "product/index");

    }

    public static ModelAndView renderProductsBySupplier(Request request, Response response) {

        Map params = new HashMap<>();

        DataStoreSwitcher switcher = new DataStoreSwitcher();

        //filter by the request id.
        params.put("category", switcher.getSupplierDao().find(Integer.parseInt(request.params(":id"))));
        params.put("products", switcher.getProductDao().getBy(switcher.getSupplierDao().find(Integer.parseInt(request.params(":id")))));
        eventHandler(switcher.getSupplierDao(), switcher.getProductCategoryDao(), params, request);

        return new ModelAndView(params, "product/index");

    }

}
