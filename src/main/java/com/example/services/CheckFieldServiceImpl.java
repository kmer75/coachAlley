package com.example.services;

import com.example.Utils.CheckEmailException;
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
public class CheckFieldServiceImpl implements CheckFieldService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    TokenService tokenService;

    @Override
    public void verifierEmailInscription(String email, HttpServletRequest hsr) throws MessagingException, CheckEmailException{

        User user = userRepository.findUserByEmail(email);
        if(user != null) {
            if(!user.isEnabled()) {
                //envoie du mail
                String url = UrlContextPath.getURLWithContextPath(hsr);
                Token t = new Token(tokenService.createToken());
                t.setUser(user);
                tokenService.save(t);
                emailSenderService.envoyerMailDuToken(user, t, url);
                System.out.println("envoie du mail");
                throw new CheckEmailException("cet email est dejà enregistré mais n'a pas été activé, nous vous envoyons un mail pour le réactiver");
            } else throw new CheckEmailException("cet email est déjà utilisé");
        }

    }
}
