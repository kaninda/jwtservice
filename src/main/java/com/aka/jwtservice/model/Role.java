package com.aka.jwtservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name  = "T_ROLE")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "role_id")
    private Long roleId;
    @Column (name = "role_name")
    private String roleName;
}
