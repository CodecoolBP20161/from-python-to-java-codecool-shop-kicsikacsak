package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class ProductController {
    public static boolean LOGIN_ERROR = false;
    public static boolean REGISTRATION_ERROR = false;
    public static boolean USER_SAVED = false;

    //handle the basic rendering, and the session
    private static void eventHandler(SupplierDao supplierDataStore, ProductCategoryDao productCategoryDataStore, Map<Object, Object> params, Request req) {
        params.put("allcategories", productCategoryDataStore.getAll());
        params.put("allsuppliers", supplierDataStore.getAll());

        Integer cartlength = 0;
        HashMap cartContent = null;
        Float totalSum = 0.0f;
        User user = null;
        try {
            Cart cart = req.session().attribute("cart");
            user = req.session().attribute("user");
            cartlength = cart.allProducts();
            cartContent = cart.getProducts();
            totalSum = cart.getTotalSum();

        } catch (NullPointerException e) {

        }

        params.put("saveduser", USER_SAVED);
        params.put("loginerror", LOGIN_ERROR);
        params.put("registrationerror", REGISTRATION_ERROR);
        params.put("user", user);
        params.put("cartlength", cartlength);
        params.put("cart", cartContent);
        params.put("totalsum", totalSum);
        LOGIN_ERROR = false;
        REGISTRATION_ERROR = false;
        USER_SAVED = false;


        BannerServiceController bannerServiceController = new BannerServiceController();
        try {
            if(user == null){
            JSONObject jsonObject = new JSONObject(bannerServiceController.getBanner());
            params.put("banner", jsonObject.get("Advertisement"));}

            if(user != null){
                Cart cart = req.session().attribute("cart");
                JSONObject jsonObject1 = new JSONObject(bannerServiceController.getBannerByUsernameAndCart(user, cart));
                System.out.println("------------------------------"+ cart.getProducts().toString());
                params.put("banner2", jsonObject1.get("Advertisement"));}

        }catch (IOException | URISyntaxException e) {
            System.out.print(e);
        }

    }

    public static ModelAndView renderProducts(Request req, Response res) throws IOException, URISyntaxException {

        Map<Object, Object> params = new HashMap<>();

        params.put("category", DataStoreSwitcher.getProductCategoryDao().find(1));
        params.put("products", DataStoreSwitcher.getProductDao().getAll());
        eventHandler(DataStoreSwitcher.getSupplierDao(), DataStoreSwitcher.getProductCategoryDao(), params, req);
        params.put("allproducts", "All Products");

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsByCategory(Request request, Response response) {

        Map<Object, Object> params = new HashMap<>();


        //filter by the request id.
        params.put("category", DataStoreSwitcher.getProductCategoryDao().find(Integer.parseInt(request.params(":id"))));
        params.put("products", DataStoreSwitcher.getProductDao().getBy(DataStoreSwitcher.getProductCategoryDao().find(Integer.parseInt(request.params(":id")))));
        eventHandler(DataStoreSwitcher.getSupplierDao(), DataStoreSwitcher.getProductCategoryDao(), params, request);


        return new ModelAndView(params, "product/index");

    }

    public static ModelAndView renderProductsBySupplier(Request request, Response response) {

        Map<Object, Object> params = new HashMap<>();

        //filter by the request id.
        params.put("category", DataStoreSwitcher.getSupplierDao().find(Integer.parseInt(request.params(":id"))));
        params.put("products", DataStoreSwitcher.getProductDao().getBy(DataStoreSwitcher.getSupplierDao().find(Integer.parseInt(request.params(":id")))));
        eventHandler(DataStoreSwitcher.getSupplierDao(), DataStoreSwitcher.getProductCategoryDao(), params, request);

        return new ModelAndView(params, "product/index");

    }

    public static ModelAndView renderCheckout(Request request, Response response) {
        Map<Object, Object> params = new HashMap<>();
        eventHandler(DataStoreSwitcher.getSupplierDao(), DataStoreSwitcher.getProductCategoryDao(), params, request);
        return new ModelAndView(params, "product/checkout");
    }

}
