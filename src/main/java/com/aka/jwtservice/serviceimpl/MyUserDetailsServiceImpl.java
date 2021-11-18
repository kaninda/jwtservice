package com.aka.jwtservice.serviceimpl;

import com.aka.jwtservice.model.User;
import com.aka.jwtservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public MyUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findByUserName(username);
        Collection <SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(
                role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                }
        );
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(), authorities);
    }
}
