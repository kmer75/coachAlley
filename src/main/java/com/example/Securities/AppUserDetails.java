package com.example.Securities;

import com.example.entities.Role;
import com.example.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kevin on 13/10/2016.
 */
public class AppUserDetails implements UserDetails {


    private User user; //je mets un User en variable pour l'utiliser ds mes methode d'authentification


    //constructeur par defaut
    public AppUserDetails() {
    }

    //constructeur avec User
    public AppUserDetails(User user) {
        this.user = user;
    }

    //getter user
    public User getUser() {
        return user;
    }

    //setter user
    public void setUser(User user) {
        this.user = user;
    }

    /* Methodes de l'interface UserDetails*/

    //methodes droit d'acces par les roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
