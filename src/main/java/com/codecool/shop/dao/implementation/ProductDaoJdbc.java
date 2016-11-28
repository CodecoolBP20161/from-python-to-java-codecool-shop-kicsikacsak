package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.List;


public class ProductDaoJdbc implements ProductDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    @Override
    public void add(Product product) {

        String query = "INSERT INTO product (name, default_price, currency, description, category, supplier) VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getDefaultPrice());
            preparedStatement.setObject(3, product.getDefaultCurrency());
            preparedStatement.setInt(4, product.getProductCategory().getId());
            preparedStatement.setInt(5, product.getSupplier().getId());

            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {

    }

    @Override
    public List<Product> getBy(Supplier supplier) {

    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {

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
