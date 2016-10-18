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
    public String verifierEmailInscription(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            return "notExist";
        } else {

            if (!user.isEnabled()) {
                return "disabled";
            } else return "exist";
        }

    }

}

