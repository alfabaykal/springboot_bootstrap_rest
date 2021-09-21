package com.alfabaykal.spring.springboot_bootstrap_rest.dao;

import com.alfabaykal.spring.springboot_bootstrap_rest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role getRoleByName(String name);
}
