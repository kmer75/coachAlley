package com.example.Securities;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by kevin on 13/10/2016.
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        User user = userRepository.findUserByEmail(s);

        if (user == null) {
            System.out.println("User not found!!!");
            throw new UsernameNotFoundException("username not found => "+ s);
        }
        return new AppUserDetails(user);
    }
}
