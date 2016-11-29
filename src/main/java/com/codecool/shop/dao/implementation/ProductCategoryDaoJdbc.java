package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by svindler on 28.11.2016.
 */
public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "svindler";
    private static final String DB_PASSWORD = "codecool";

    private static ProductCategoryDaoJdbc instance = null;

    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance(){
        if (instance == null){
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category){
        String query = "INSERT INTO categories (category_id, name, department, description) VALUES (?, ?, ?, ?);";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setString(3, category.getDepartment());
            preparedStatement.setObject(4, category.getDescription());

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductCategory find(int id){
        String query = "SELECT * FROM category WHERE id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {
            if(resultSet.next()){
                ProductCategory result = new ProductCategory(resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                return result;
            }else{
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void remove(int id){
        String query = "DELETE FROM category WHERE id ='" + id + "';";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    @Override
    public List<ProductCategory> getAll(){

        String query = "SELECT * FROM category;";
        List<ProductCategory> categories = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){

                ProductCategory productCategory = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")
                );
                categories.add(productCategory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
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
