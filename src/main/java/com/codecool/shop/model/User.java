package com.codecool.shop.model;

/**
 * Created by svindler on 12.12.2016.
 */
public class User {

    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email ){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}
