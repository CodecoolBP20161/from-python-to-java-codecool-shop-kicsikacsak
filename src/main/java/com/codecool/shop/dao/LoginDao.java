package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;

import java.util.List;

/**
 * Created by svindler on 12.12.2016.
 */
public interface LoginDao {
    void add(User user);
    User find(String username, String password);
    void remove(int id);
    boolean checkExistingUsername(String username);
}
