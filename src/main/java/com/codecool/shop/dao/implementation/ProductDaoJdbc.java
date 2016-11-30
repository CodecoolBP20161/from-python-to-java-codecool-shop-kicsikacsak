package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dbconnection.Connector;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoJdbc implements ProductDao {

    private static ProductDaoJdbc instance = null;

    private static Connector connector = Connector.getInstance();

    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance(){
        if (instance == null){
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {



        String query = "INSERT INTO product (name, default_price, currency, description, category, supplier) VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getDefaultPrice());
            preparedStatement.setObject(3, "USD");
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setInt(5, product.getProductCategory().getId());
            preparedStatement.setInt(6, product.getSupplier().getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {

        String query = "SELECT * FROM product WHERE id ='" + id + "';";

        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                ProductCategoryDaoJdbc productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
                ProductCategory pcategory = productCategoryDaoJdbc.find(resultSet.getInt("category_id"));
                SupplierDaoJdbc supplierDaoJdbc = SupplierDaoJdbc.getInstance();
                Supplier supplier = supplierDaoJdbc.find(resultSet.getInt("supplier_id"));
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        pcategory,
                        supplier);

                return product;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void remove(int id) {

        String query = "DELETE FROM product WHERE id = '" + id +"';";
        connector.executeQuery(query);

    }

    @Override
    public List<Product> getAll() {

        String query = "SELECT * FROM product;";

        List<Product> productList = new ArrayList<>();



        try (Connection connection = connector.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

        ){
            while (resultSet.next()){

                ProductCategoryDaoJdbc productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
                ProductCategory pcategory = productCategoryDaoJdbc.find(resultSet.getInt("category"));
                SupplierDaoJdbc supplierDaoJdbc = SupplierDaoJdbc.getInstance();
                Supplier supplier = supplierDaoJdbc.find(resultSet.getInt("supplier"));

                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        pcategory,
                        supplier);
                    productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;

    }

    @Override
    public List<Product> getBy(Supplier supplier) {

        String query = "SELECT * FROM product WHERE supplier = '" + supplier.getId() +"';";
        List<Product> productsBySupplier = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));

                ProductCategoryDaoJdbc productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
                ProductCategory pcategory = productCategoryDaoJdbc.find(resultSet.getInt("category"));
                SupplierDaoJdbc supplierDaoJdbc = SupplierDaoJdbc.getInstance();

                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        pcategory,
                        supplier);
                productsBySupplier.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsBySupplier;

    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        System.out.println(productCategory);
        String query = "SELECT * FROM product WHERE category = '" + productCategory.getId() +"';";

        List<Product> productsByCategory = new ArrayList<>();
        try (Connection connection = connector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {

                ProductCategoryDaoJdbc productCategoryDaoJdbc = ProductCategoryDaoJdbc.getInstance();
                SupplierDaoJdbc supplierDaoJdbc = SupplierDaoJdbc.getInstance();
                Supplier supplier = supplierDaoJdbc.find(resultSet.getInt("supplier"));

                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        productCategory,
                        supplier);
                productsByCategory.add(product);
                }
            }  catch (SQLException e) {
            e.printStackTrace();
        }
            return productsByCategory;
    }

}
