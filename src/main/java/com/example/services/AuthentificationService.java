package com.example.services;

import com.example.entities.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 18/10/2016.
 */
public interface AuthentificationService {

    void forgetPasswordProcessFormIdentification(String email, HttpServletRequest hsr) throws Exception;

    User forgetTokenExist(String token, HttpServletRequest hsr) throws Exception;

    void saveUserPasswordDeleteToken(User u, String token);

}
