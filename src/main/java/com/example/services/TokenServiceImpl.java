package com.example.services;

import com.example.entities.Token;
import com.example.entities.User;
import com.example.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by kevin on 12/10/2016.
 */
@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Token findOne(Long id) {
        return tokenRepository.findOne(id);
    }

    @Override
    public Token findTokenByUser(User user) {
        return tokenRepository.findTokenByUser(user);
    }

    @Override
    public Token findTokenByToken(String token) {
        return tokenRepository.findTokenByToken(token);
    }

    @Override
    public String createToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void delete(Token t) {
        tokenRepository.delete(t);
    }

    @Override
    public Boolean isTokenExpired(Token t) {
        if(t.getExpired().before(new Date())) return true;
        else return false;
    }
}
