import com.codecool.shop.controller.DataStoreSwitcher;
import com.codecool.shop.util.MD5Hash;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.LoginController;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.datafiller.ExampleData;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
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

        get("/filter/:id", ProductController::renderProductsByCategory, new ThymeleafTemplateEngine());

        get("/supplier/:id", ProductController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        post("/registration", (Request req, Response res) -> {
            LoginController userController = new LoginController();
            User user = new User(req.queryParams("username"),
                    MD5Hash.convertToHash(req.queryParams("password")),
                    req.queryParams("email"));
            if(!userController.checkExistingUser(req.queryParams("username"))) {
                LoginController.saveUser(user);
            }
            res.redirect("/");
            return "";
        });

        get("/checkout", ProductController::renderCheckout, new ThymeleafTemplateEngine());

        get("/logout", (Request req, Response res) -> {
            req.session().attribute("user", null);
            res.redirect("/");
            return "";
        });


        post("/login", (Request req, Response res) -> {
            LoginController userController = new LoginController();
            User user  = null;
            if (req.session().attribute("user") == null) {
                user = userController.checkUser(req.queryParams("username"), req.queryParams("password"));
            } else {
                user = req.session().attribute("user");
            }
            req.session().attribute("user", user);
            res.redirect("/");
            return "";
        });

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
