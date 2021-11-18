package com.aka.jwtservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_USER")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private String name;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name ="t_user_role",
    joinColumns = @JoinColumn(name ="user_id"),
    inverseJoinColumns = @JoinColumn(name ="role_id"))
    private List<Role> roles;
}
