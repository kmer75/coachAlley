package com.example.services;


import com.example.Utils.CheckEmailException;
import com.example.entities.Token;
import com.example.entities.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by kevin on 12/10/2016.
 */

public interface UserService {

    User findOne(Long id);

    User findUserByEmail(String email);

    User findUserByTokenToken(String token);

    List<User> findAll();

    User save(User user);

    void forgetPasswordEnvoieMail(String email) throws CheckEmailException;
}
