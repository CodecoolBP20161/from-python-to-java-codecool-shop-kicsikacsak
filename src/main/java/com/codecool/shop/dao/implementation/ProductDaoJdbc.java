package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoJdbc implements ProductDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    @Override
    public void add(Product product) {

        String query = "INSERT INTO product (name, default_price, currency, description, category, supplier) VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getDefaultPrice());
            preparedStatement.setObject(3, product.getDefaultCurrency());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setInt(5, product.getProductCategory().getId());
            preparedStatement.setInt(6, product.getSupplier().getId());

            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {

        String query = "SELECT * FROM product WHERE id ='" + id + "';";

        try (Connection connection = getConnection();
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
        executeQuery(query);

    }

    @Override
    public List<Product> getAll() {

        String query = "SELECT * FROM product;";

        List<Product> productList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("supplier_id"));
                    productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;

    }

    @Override
    public List<Product> getBy(Supplier supplier) {

        String query = "SELECT * FROM product WHERE id = '" + supplier.getId() +"';";

        List<Product> productsBySupplier = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt();
                productsBySupplier.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsBySupplier;

    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {

        String query = "SELECT * FROM proruct WHERE id = '" + productCategory.getId() +"';";

        List<Product> productsByCategory = new ArrayList<>();
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("supplier_id"));
                productsByCategory.add(product);
                }
            }  catch (SQLException e) {
            e.printStackTrace();
        }
            return productsByCategory;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
