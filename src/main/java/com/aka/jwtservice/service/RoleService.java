package com.aka.jwtservice.service;

import com.aka.jwtservice.model.Role;

public interface RoleService {
    public void createRole (Role role);
    public void deletingRole (String roleName);
}
