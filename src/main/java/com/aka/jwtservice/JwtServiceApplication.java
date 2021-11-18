package com.aka.jwtservice;

import com.aka.jwtservice.model.Role;
import com.aka.jwtservice.model.User;
import com.aka.jwtservice.service.RoleService;
import com.aka.jwtservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtServiceApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run (UserService userService, RoleService roleService, PasswordEncoder encoder) {
       return args -> {
            /*  roleService.createRole(new Role(null, "ROLE_ADMIN"));


            userService.createUser( new User(null, "arnaud.kaninda@gmail.com", encoder.encode("onclekani"),"Kaninda", new ArrayList<>() ));

            userService.addRoleToUser("arnaud.kaninda@gmail.com", "ROLE_ADMIN");*/

        };
    }

}
