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
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

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
        String query = "INSERT INTO category (name, department, description) VALUES (?, ?, ?);";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDepartment());
            preparedStatement.setObject(3, category.getDescription());

            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductCategory find(int id){
        String query = "SELECT * FROM category WHERE id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);)
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
