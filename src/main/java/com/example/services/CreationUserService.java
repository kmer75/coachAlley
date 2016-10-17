package com.example.services;

import com.example.entities.Token;
import com.example.entities.User;

/**
 * Created by kevin on 18/10/2016.
 */
public interface CreationUserService {

    public void creerUser(User user, Token token);
}
