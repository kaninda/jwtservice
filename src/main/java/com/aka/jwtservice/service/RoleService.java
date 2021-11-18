package com.aka.jwtservice.service;

import com.aka.jwtservice.model.Role;

import java.util.List;

public interface RoleService {
    public Role createRole (Role role);
    public void deletingRole (String roleName);
    public List<Role> getAllRoles ();
    public Role findByRoleName (String roleName);
}
