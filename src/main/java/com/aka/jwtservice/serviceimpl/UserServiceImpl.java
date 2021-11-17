package com.aka.jwtservice.serviceimpl;

import com.aka.jwtservice.model.Role;
import com.aka.jwtservice.model.User;
import com.aka.jwtservice.repository.RoleRepository;
import com.aka.jwtservice.repository.UserRepository;
import com.aka.jwtservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,  RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void createUser(User user) {
        log.info("Create User => {}", user.getUserName());
       userRepository.save (user);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Retrieving all users ");
       return userRepository.findAll();
    }

    @Override
    public User getUserName(String userName) {
        log.info("Finding user by the his name => {}", userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding Role => {} to the user => {}", roleName, userName);
        final User user = userRepository.findByUserName(userName);
        final Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void deleteRoleToUSer(String userName, String roleName) {
        log.info("Deleting Role => {} to the user => {}", roleName, userName);
        final User user = userRepository.findByUserName(userName);
        final Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().remove(role);
    }

    @Override
    public void deleteUser(String userName) {
        log.info("Deleting User => {}", userName);
        final User user = userRepository.findByUserName(userName);
        userRepository.delete(user);

    }
}
