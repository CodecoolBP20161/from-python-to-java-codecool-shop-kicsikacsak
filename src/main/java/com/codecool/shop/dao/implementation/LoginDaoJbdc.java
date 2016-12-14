package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.LoginDao;
import com.codecool.shop.dbconnection.Connector;
import com.codecool.shop.model.User;

import java.sql.*;


public class LoginDaoJbdc implements LoginDao {

    private static LoginDaoJbdc instance = null;

    private static Connector connector = Connector.getInstance();

    private LoginDaoJbdc() {
    }

    public static LoginDaoJbdc getInstance(){
        if (instance == null){
            instance = new LoginDaoJbdc();
        }
        return instance;
    }

    public void add(User user) {
        String query = "INSERT INTO webshopuser (username, email, password) VALUES (?, ?, ?);";
        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User find(String username, String password) {
        String query = "SELECT * FROM webshopuser WHERE username ='" + username + "' AND password='" + password +"';";

        try (Connection connection = connector.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {
            if(resultSet.next()){
                return  new User(
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }else{
                ProductController.LOGIN_ERROR = true;
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkExistingUsername(String username) {
        String query = "SELECT * FROM webshopuser WHERE username ='" + username + "';";

        try (Connection connection = connector.getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {
            if(resultSet.next()){
                ProductController.REGISTRATION_ERROR = true;
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void remove(int id) {

    }
}
