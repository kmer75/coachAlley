package com.example.services;

import com.example.Utils.TokenType;
import com.example.Utils.UrlContextPath;
import com.example.entities.Token;
import com.example.entities.User;
import com.example.repositories.UserRepository;
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
    CheckFieldService checkFieldService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;

    @Override
    public void forgetPasswordProcessFormIdentification(String email, HttpServletRequest hsr) throws Exception {
        User u = userRepository.findUserByEmail(email);
        if(checkFieldService.verifierUserEmail(email).equals("exist")) {
            Token t = tokenService.createAndSaveToken(u, ""+TokenType.FORGET);
            emailSenderService.envoyerMailDuMdpOublie(u,t, hsr);

        } else if(checkFieldService.verifierUserEmail(email).equals("disabled")){
            Token t = tokenService.createAndSaveToken(u, ""+TokenType.ACTIVE);
            emailSenderService.envoyerMailDuToken(u,t, hsr);
            throw new Exception("ce compte existe mais n'a pas été activé, un email vous a été envoyé afin de l'activer");

        } else if(checkFieldService.verifierUserEmail(email).equals("notExist")) throw new Exception("ce compte n'existe pas");

    }

    @Override
    public User forgetTokenExist(String token, HttpServletRequest hsr) throws Exception{
        Token t =tokenService.findTokenByToken(token);
        if(t == null || !t.getType().equals(""+TokenType.FORGET)) throw new Exception("Ce lien n'existe pas");
        User u = t.getUser();
        if(tokenService.isTokenExpired(t)) {
            t = tokenService.createAndSaveToken(u, ""+TokenType.FORGET);
            emailSenderService.envoyerMailDuMdpOublie(u, t, hsr);
            throw new Exception("ce lien a expiré, un nouveau lien vous a été envoyé par mail");
        }
        return u;
    }

    @Override
    public void saveUserPasswordDeleteToken(User u, String token) {
        userRepository.save(u);
        tokenService.delete(tokenService.findTokenByToken(token));
    }
}
