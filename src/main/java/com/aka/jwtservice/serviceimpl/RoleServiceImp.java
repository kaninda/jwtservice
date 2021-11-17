package com.aka.jwtservice.serviceimpl;

import com.aka.jwtservice.model.Role;
import com.aka.jwtservice.repository.RoleRepository;
import com.aka.jwtservice.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void createRole(Role role) {
        log.info("Saving a new Role => {}", role.getRoleName());
        roleRepository.save(role);
    }

    @Override
    public void deletingRole(String roleName) {
        log.info("Deleting Role => {}", roleName);
    }
}
