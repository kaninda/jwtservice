package com.aka.jwtservice.controller;

import com.aka.jwtservice.model.Role;
import com.aka.jwtservice.model.User;
import com.aka.jwtservice.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequestMapping(name="refreshToken", value="/api/token", produces = MediaType.APPLICATION_JSON_VALUE)

public class RefreshToken {

    private final UserService userService;

    @Autowired
    public RefreshToken(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/refresh")
    public void refreshToken (HttpServletRequest request,  HttpServletResponse response) throws IOException {

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                final String refresh_token = authorizationHeader.substring("Bearer ".length());
                final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                final JWTVerifier verifier = JWT.require(algorithm).build();
                final DecodedJWT decodedJWT = verifier.verify(refresh_token);
                final String userName = decodedJWT.getSubject();
                final User user = userService.findByUserName(userName);
                String access_token = JWT.create().withSubject(user.getUserName()).
                        withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            }catch(Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());
                Map<String, String> errors = new HashMap<>();
                errors.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), errors);

            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
