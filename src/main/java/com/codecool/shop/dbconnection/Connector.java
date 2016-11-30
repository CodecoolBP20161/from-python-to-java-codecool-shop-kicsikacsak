package com.codecool.shop.dbconnection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by doramedgyasszay on 2016. 11. 30..
 */
public class Connector implements ConnectorInterface {

    private String DATABASE;
    private String DB_USER;
    private String DB_PASSWORD;

    private static Connector connectorInstance = null;

    private Connector() {
    }

    public static Connector getInstance(){
        if (connectorInstance == null){
            connectorInstance = new Connector();
        }
        return connectorInstance;
    }

    public Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/connection.properties"));
        } catch (Exception e){
            System.out.println("No file found");
        }

        DATABASE = props.getProperty("database");
        DB_USER = props.getProperty("user");
        DB_PASSWORD = props.getProperty("password");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
        } catch (Exception e) {
            System.out.println("Invalid connection properties");
        }
        return connection;
    }

    public void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
