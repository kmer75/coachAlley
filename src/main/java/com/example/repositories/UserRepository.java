package com.example.repositories;

import com.example.entities.Role;
import com.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kevin on 12/10/2016.
 */
public interface UserRepository extends JpaRepository<User, Long>{

    User findUserByEmail(String email);

    List<User> findByRolesAndDeleted(List<Role> roles, boolean isDeleted);

    User findUserByTokenToken(String token);

    User findUserByTokenTokenAndTokenType(String token, String type);
}
