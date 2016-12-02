package com.codecool.shop.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by doramedgyasszay on 2016. 11. 30..
 */
public interface ConnectorInterface {

    Connection getConnection() throws SQLException;
    void executeQuery(String query);

}
