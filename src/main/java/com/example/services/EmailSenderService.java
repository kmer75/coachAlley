package com.example.services;

import com.example.entities.Token;
import com.example.entities.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 17/10/2016.
 */
public interface EmailSenderService {

    public void send(String to, String subject, String content) throws MessagingException ;

    public void envoyerMailDuToken(User user, Token token, HttpServletRequest hsr) throws MessagingException;

    public void envoyerMailDuMdpOublie(User user, Token token, HttpServletRequest hsr) throws MessagingException;
}
