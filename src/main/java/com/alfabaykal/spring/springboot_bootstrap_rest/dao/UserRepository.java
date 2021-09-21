package com.alfabaykal.spring.springboot_bootstrap_rest.dao;

import com.alfabaykal.spring.springboot_bootstrap_rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String name);
}
