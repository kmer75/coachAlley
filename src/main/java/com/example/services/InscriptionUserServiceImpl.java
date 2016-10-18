package com.example.services;

import com.example.Utils.CheckEmailException;
import com.example.Utils.TokenType;
import com.example.Utils.UrlContextPath;
import com.example.entities.Role;
import com.example.entities.Token;
import com.example.entities.User;
import com.example.repositories.RoleRepository;
import com.example.repositories.TokenRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 17/10/2016.
 */
@Service
public class InscriptionUserServiceImpl implements InscriptionUserService{

    @Autowired
    TokenService tokenService;

    @Autowired
    CreationUserService creationUserService;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CheckFieldService checkFieldService;


    @Override
    public void creerUser(User u, HttpServletRequest hsr) throws MessagingException {
        Token t = new Token(tokenService.createToken(), ""+TokenType.ACTIVE);
        creationUserService.creerUser(u,t);
        emailSenderService.envoyerMailDuToken(u, t, hsr);
    }


    @Override
    public User activerCompte(String token, HttpServletRequest hsr) throws Exception{
        Token t =tokenService.findTokenByToken(token);
        if(t == null || !t.getType().equals(""+TokenType.ACTIVE)) throw new Exception("Ce lien d'activation n'existe pas");
        User u = t.getUser();
        if(tokenService.isTokenExpired(t)) {
            t = tokenService.createAndSaveToken(u, ""+TokenType.ACTIVE);
            emailSenderService.envoyerMailDuToken(u, t, hsr);
            throw new Exception("Votre lien d'activation a expiré, un nouveau lien vous a été envoyé");
        }
        u.setConfirmPassword(u.getPassword()); //pour valider validator de l'entite
        u.setEnabled(true);
        u.setAccountNonLocked(true);
        userRepository.save(u);
        tokenService.delete(t);
        return u;
    }

    @Override
    public void afficherFormulaireInscription(String email, HttpServletRequest hsr) throws CheckEmailException, MessagingException {
        if(checkFieldService.verifierUserEmail(email).equals("exist")) {
            throw new CheckEmailException("un utilisateur ayant cette adresse email existe dejà");
        } else if(checkFieldService.verifierUserEmail(email).equals("disabled")){
            User u = userRepository.findUserByEmail(email);
            Token t = tokenService.createAndSaveToken(u, ""+TokenType.ACTIVE);
            emailSenderService.envoyerMailDuToken(u,t, hsr);
            throw new CheckEmailException("votre compte a été créé mais n'est pas activé, un mail vous a été envoyé pour l'activer");
        }
    }
}
