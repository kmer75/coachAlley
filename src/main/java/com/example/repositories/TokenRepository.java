package com.example.repositories;

import com.example.entities.Token;
import com.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by kevin on 12/10/2016.
 */
public interface TokenRepository extends JpaRepository<Token, Long> {


    Token findTokenByUser(User user);

    Token findTokenByToken(String token);


}
