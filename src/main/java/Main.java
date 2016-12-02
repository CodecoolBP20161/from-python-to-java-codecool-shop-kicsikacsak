import com.codecool.shop.controller.DataStoreSwitcher;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.datafiller.ExampleData;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) {

        DataStoreSwitcher.dataStoreType = DataStoreSwitcher.DataStore.DATABASE;

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        ExampleData.populateData();

        // filter the products by category id
        get("/filter/:id", ProductController::renderProductsByCategory, new ThymeleafTemplateEngine());

        get("/supplier/:id", ProductController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        post("/cart", (Request req, Response res) -> {

            Product product = ProductDaoJdbc.getInstance().find(Integer.parseInt(req.queryParams("prodid")));
            Cart cart = null;

             if (req.session().attribute("cart") == null) {
                 cart = new Cart();
             } else {
                 cart = req.session().attribute("cart");
             }

            cart.add(product);

            req.session().attribute("cart", cart);


            res.redirect("/");
            return "";
        });


        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());


        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }
}
