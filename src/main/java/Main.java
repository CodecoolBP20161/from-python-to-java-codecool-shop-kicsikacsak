import com.codecool.shop.controller.ProductController;
import com.codecool.shop.datafiller.ExampleData;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        ExampleData.populateData();

        // filter the products by category id
        get("/filter/:id", ProductController::renderProductsByCategory, new ThymeleafTemplateEngine());

        get("/supplier/:id", ProductController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());


        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }
}
