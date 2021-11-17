package com.aka.jwtservice.repository;

import com.aka.jwtservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, Long> {
   Role findByRoleName (String roleName);
}
