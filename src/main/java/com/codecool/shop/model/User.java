package com.codecool.shop.model;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

/**
 * Created by svindler on 12.12.2016.
 */
public class User {

    private String username;
    private String password;
    private String email;

    private String address;
    private String country;
    private String  zipcode;


    private String phone;
    private String firstname;
    private String lastname;

    public User(String username, String password, String email, String address, String country, String zipcode,
                String  phone, String firstname, String lastname){
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.country = country;
        this.zipcode = zipcode;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;

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

    public String getAddress() {
        return this.address;
    }

    public String getCountry() {
        return this.country;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public String  getPhone() {
        return this.phone;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return lastname;
    }
}

