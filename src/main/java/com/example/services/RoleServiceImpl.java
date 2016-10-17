package com.example.services;

import com.example.entities.Role;
import com.example.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kevin on 12/10/2016.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findOne(Long id) {
        return roleRepository.findOne(id);
    }

    public Role findRoleByName(String role) { return roleRepository.findRoleByName(role);}

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
