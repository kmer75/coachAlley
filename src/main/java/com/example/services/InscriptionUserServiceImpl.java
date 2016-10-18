package com.example.services;

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

    @Override
    public void creerUser(User u) throws MessagingException {
        Token t = new Token(tokenService.createToken());
        creationUserService.creerUser(u,t);
        emailSenderService.envoyerMailDuToken(u, t);
    }


    @Override
    public User activerCompte(String token) throws Exception{
        User u = userRepository.findUserByTokenToken(token);
        if(u == null) {
            throw new Exception("User non trouvé");
        }
        u.setConfirmPassword(u.getPassword()); //pour valider validator de l'entite
        u.setEnabled(true);
        u.setAccountNonLocked(true);
        userRepository.save(u);
        return u;
    }

}
