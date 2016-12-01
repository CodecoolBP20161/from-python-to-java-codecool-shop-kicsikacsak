package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dbconnection.Connector;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svindler on 28.11.2016.
 */
public class SupplierDaoJdbc implements SupplierDao {

    private static SupplierDaoJdbc instance = null;

    private static Connector connector = Connector.getInstance();

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

        try (Connection connection = connector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
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

        try (Connection connection = connector.getConnection();
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
        connector.executeQuery(query);

    }

    @Override
    public List<Supplier> getAll() {

        String query = "SELECT * FROM supplier;";

        List<Supplier> supplierList = new ArrayList<>();

        try (Connection connection = connector.getConnection();
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

}
