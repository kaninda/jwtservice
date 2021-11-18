package com.aka.jwtservice.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.aka.jwtservice.model.User;
import com.aka.jwtservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(name="UserController", value="/api/user", produces = MediaType.APPLICATION_JSON_VALUE)

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers () {
        final List<User> users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/find")
    public ResponseEntity<User> findByName(@RequestParam String userName){
        final User  user = userService.findByUserName(userName);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody User user){
        URI uri  = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/create").toString());
        final User userSaved = userService.createUser(user);
        return ResponseEntity.created(uri).body(userSaved);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam String userName){
        userService.deleteUser(userName);
        return ResponseEntity.ok().build();
    }

}
