package com.aka.jwtservice.repository;

import com.aka.jwtservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {
    User findByUserName (String userName);
}
