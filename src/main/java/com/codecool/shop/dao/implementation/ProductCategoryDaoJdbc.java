package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dbconnection.Connector;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svindler on 28.11.2016.
 */

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
<<<<<<< HEAD
=======
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "mz";
    private static final String DB_PASSWORD = "tdk";
>>>>>>> bd77f48f597d0d36a8aa2b6ea75c328eec82d8cf

    private static ProductCategoryDaoJdbc instance = null;

    private static Connector connector = Connector.getInstance();

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

        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setString(3, category.getDepartment());
            preparedStatement.setObject(4, category.getDescription());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductCategory find(int id){
        String query = "SELECT * FROM categories WHERE category_id ='" + id + "';";

        try (Connection connection = connector.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {
            if(resultSet.next()){
                ProductCategory result = new ProductCategory(
                        resultSet.getInt("category_id"),
                        resultSet.getString("name"),
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

        String query = "DELETE FROM categories WHERE category_id = '" + id +"';";
        connector.executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll(){

        String query = "SELECT * FROM categories;";
        List<ProductCategory> categories = new ArrayList<>();

        try (Connection connection = connector.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){

                ProductCategory productCategory = new ProductCategory(
                        resultSet.getInt("category_id"),
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

}
