package com.example.services;

import com.example.entities.Token;
import com.example.entities.User;

import javax.mail.MessagingException;

/**
 * Created by kevin on 17/10/2016.
 */
public interface EmailSenderService {

    public void send(String to, String subject, String content) throws MessagingException ;

    public void sendHtmlMail(User user, String url) throws MessagingException ;

    public void envoyerMailDuToken(User user, Token token, String urlNomDomain) throws MessagingException;
}
