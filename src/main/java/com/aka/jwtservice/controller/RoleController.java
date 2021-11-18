package com.aka.jwtservice.controller;

import com.aka.jwtservice.model.Role;
import com.aka.jwtservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(name="/api/role")
public class RoleController {

    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles () {
        final List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok().body(roles);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody Role role){
        final Role roleSaved = roleService.createRole(role);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/create").toString());
        return ResponseEntity.created(uri).body(roleSaved);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam String roleName ) {
        roleService.deletingRole(roleName);
        return ResponseEntity.ok().build();
    }
}
