package com.example.repositories;

import com.example.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 12/10/2016.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findRoleByName(String name);

    List<Role> findByNameIn(ArrayList<String> roles);
}
