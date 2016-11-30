package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Random;

/**
 * Created by svindler on 28.11.2016.
 */
public class SupplierDaoJdbc implements SupplierDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "svindler";
    private static final String DB_PASSWORD = "codecool";

    private static SupplierDaoJdbc instance = null;

    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance(){
        if (instance == null){
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {

        String query = "INSERT INTO supplier (supplier_id, name) VALUES (?, ?);";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, supplier.getId());
            preparedStatement.setString(2, supplier.getName());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Supplier find(int id) {

        String query = "SELECT * FROM supplier WHERE supplier_id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            if (resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("name"));

                return supplier;
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

        String query = "DELETE FROM suppliers WHERE id = '" + id +"';";
        executeQuery(query);

    }

    @Override
    public List<Supplier> getAll() {

        String query = "SELECT * FROM supplier;";

        List<Supplier> supplierList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Supplier supplier = new Supplier(
                        resultSet.getInt("supplier_id"),
                        resultSet.getString("name"));
                supplierList.add(supplier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplierList;
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
