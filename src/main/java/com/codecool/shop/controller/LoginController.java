package com.codecool.shop.controller;

import com.codecool.shop.dao.LoginDao;
import com.codecool.shop.dao.implementation.LoginDaoJbdc;
import com.codecool.shop.model.User;

import com.codecool.shop.util.MD5Hash;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by svindler on 12.12.2016.
 */

public class LoginController {

    public static void saveUser(User user) {

        EmailSenderServiceController emailSenderServiceController = new EmailSenderServiceController();
        try {
            emailSenderServiceController.isRunning();
        }catch (IOException | URISyntaxException e ) {
            System.out.println("The email sender Service is not running");
        }
        try {
            emailSenderServiceController.sendMail(user.getEmail());
        }catch (IOException | URISyntaxException e) {
            System.out.println("Cant send the e-mail");
            System.out.println(e);
        }
        LoginDao loginDao = LoginDaoJbdc.getInstance();
        loginDao.add(user);
    }

    public void removeUser(User user) {
        throw new NotImplementedException();

    }

    public User checkUser(String  username, String password) {
        LoginDao loginDao = LoginDaoJbdc.getInstance();
        try {
            password = MD5Hash.convertToHash(password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return loginDao.find(username, password);

    }
    public boolean checkExistingUser(String username) {
        LoginDao loginDao = LoginDaoJbdc.getInstance();
        return loginDao.checkExistingUsername(username);
    }
}
