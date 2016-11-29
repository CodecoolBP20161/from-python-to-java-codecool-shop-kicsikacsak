package com.codecool.shop.datafiller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by doramedgyasszay on 2016. 11. 29..
 */
public class DataBuilder {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillUpTable() {
        String[] queries = {
                "insert into product (name, default_price, currency, description, category, supplier) values ('A', 'X', '123')",
                "insert into category (name, department, description) values ('Mobile', 'Electronic', 'This is interest')",
                "insert into supplier (name) values ('Apple')",
        };

        Connection connection = getConnection();
        Statement statement = connection.createStatement();


    }
}
