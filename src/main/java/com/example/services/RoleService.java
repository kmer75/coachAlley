package com.example.services;

import com.example.entities.Role;

import java.util.List;

/**
 * Created by kevin on 12/10/2016.
 */
public interface RoleService {

    Role save(Role role);

    Role findOne(Long id);

    Role findRoleByName(String role);

    List<Role> findAll();
}
