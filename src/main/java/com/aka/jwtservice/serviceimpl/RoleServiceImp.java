package com.aka.jwtservice.serviceimpl;

import com.aka.jwtservice.model.Role;
import com.aka.jwtservice.repository.RoleRepository;
import com.aka.jwtservice.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        log.info("Saving a new Role => {}", role.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void deletingRole(String roleName) {
        log.info("Deleting Role => {}", roleName);
        final Role role = roleRepository.findByRoleName(roleName);
        roleRepository.delete(role);
    }

    @Override
    public List<Role> getAllRoles() {
        log.info("Retrieving all users");
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleName(String roleName) {
        log.info("Finding Role {} by name", roleName);
        return roleRepository.findByRoleName(roleName);
    }
}
