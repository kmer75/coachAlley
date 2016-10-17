package com.example;

import com.example.entities.Role;
import com.example.entities.User;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
/*
		RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
		UserRepository userRepository = ctx.getBean(UserRepository.class);
		Role r = new Role("ADMIN");
		roleRepository.save(r);
		Role r2 = new Role("CLIENT");
		roleRepository.save(r2);
		Role r3 = new Role("COACH");
		roleRepository.save(r3);
*/
	}
}
