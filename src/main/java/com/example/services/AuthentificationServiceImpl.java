package com.example.services;

import com.example.Utils.UrlContextPath;
import com.example.entities.Token;
import com.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 18/10/2016.
 */
@Service
public class AuthentificationServiceImpl implements AuthentificationService {

    @Autowired
    TokenService tokenService;

    @Autowired
    InscriptionUserService inscriptionUserService;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public void creerUser(User u, HttpServletRequest hsr) throws MessagingException{
        Token t = new Token(tokenService.createToken());
        String url = UrlContextPath.getURLWithContextPath(hsr);
        inscriptionUserService.inscrireUser(u,t);
        emailSenderService.envoyerMailDuToken(u, t, url);
    }
}
