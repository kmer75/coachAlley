package com.example.services;

import com.example.entities.Token;
import com.example.entities.User;

import java.util.List;

/**
 * Created by kevin on 12/10/2016.
 */
public interface TokenService {

    Token save(Token token);

    Token findOne(Long id);

    Token findTokenByUser(User user);

    Token findTokenByToken(String token);

    String createToken();

    void delete(Token t);

    Boolean isTokenExpired(Token t);

    Token createAndSaveToken(User u, String type);
}
