package com.example.services;

import com.example.Utils.CheckEmailException;
import com.example.Utils.UrlContextPath;
import com.example.entities.Role;
import com.example.entities.Token;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by kevin on 12/10/2016.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    TokenService tokenService;

    @Autowired
    EmailSenderServiceImpl emailSender;

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findUserByTokenToken(String token) {
        return userRepository.findUserByTokenToken(token);
    }

    @Override
    public User findUserByTokenTokenAndTokenType(String token, String type) {
        return userRepository.findUserByTokenTokenAndTokenType(token, type);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public void forgetPasswordEnvoieMail(String email) throws CheckEmailException {
        User user = userRepository.findUserByEmail(email);
        String uuid = UUID.randomUUID().toString();
        String [] mdp = uuid.split("-");
        if(user != null) {
            if(!user.isEnabled()) {
                throw new CheckEmailException("Votre compte n'a pas été activé");
            } else {
                    try {
                        emailSender.send(user.getEmail(), "mot de passe oublié", "Un nouveau mot de passe vous a été assigné : " + mdp[0]);
                        user.setPasswordAndConfirmPassword(mdp[0]);
                        this.save(user);
                        } catch (MessagingException e) {
                            System.out.println("email non envoyé");
                            throw new CheckEmailException("Il y a eu un problème lors de l'envoie du mail de réinitialisation du mot de passe, veuillez réessayer ultérieurement");
                         }
                    }
            } else throw new CheckEmailException("Aucun compte ne correspond à cette adresse email");
    }

}
