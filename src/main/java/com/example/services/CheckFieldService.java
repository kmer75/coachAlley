package com.example.services;

import com.example.Utils.CheckEmailException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 18/10/2016.
 */
public interface CheckFieldService {
    public void verifierEmailInscription(String email, HttpServletRequest hsr) throws MessagingException, CheckEmailException;
}
