package com.aka.jwtservice.service;

import com.aka.jwtservice.model.Role;
import com.aka.jwtservice.model.User;

import java.util.List;

public interface UserService {

    public User createUser (User user);
    public List<User> findAllUsers ();
    public User findByUserName(String userName);
    public void addRoleToUser (String userName, String roleName);
    public void deleteRoleToUSer (String userName, String roleName);
    public void deleteUser (String userName);

}
