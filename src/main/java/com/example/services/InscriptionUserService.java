package com.example.services;

import com.example.entities.Token;
import com.example.entities.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 17/10/2016.
 */
public interface InscriptionUserService {

    public void creerUser(User u) throws MessagingException;

    public User activerCompte(String token) throws Exception;

}
