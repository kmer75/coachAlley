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
    public String creerPasswordTemporaire() {
        String uuid = UUID.randomUUID().toString();
        String [] mdp = uuid.split("-");
        return mdp[0];
    }


}
