package com.example.services;

import com.example.Utils.CheckEmailException;
import com.example.entities.Token;
import com.example.entities.User;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 17/10/2016.
 */
@Service
public interface InscriptionUserService {

    public void creerUser(User u) throws MessagingException;

    public User activerCompte(String token) throws Exception;

    public void afficherFormulaireInscription(String email) throws CheckEmailException, MessagingException;

}
