package com.example.services;

import com.example.entities.Role;
import com.example.entities.Token;
import com.example.entities.User;
import com.example.repositories.RoleRepository;
import com.example.repositories.TokenRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kevin on 18/10/2016.
 */
@Service
public class CreationUserServiceImpl implements CreationUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TokenRepository tokenRepository;


    @Override
    public void creerUser(User user, Token token) {
        Role role = roleRepository.findRoleByName("COACH");
        user.getRoles().add(role);
        token.setUser(user);
        userRepository.save(user);
        tokenRepository.save(token);
    }
}
