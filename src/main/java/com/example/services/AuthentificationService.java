package com.example.services;

import com.example.entities.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 18/10/2016.
 */
public interface AuthentificationService {

    public void creerUser(User u, HttpServletRequest hsr) throws MessagingException;

}
