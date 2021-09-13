package com.alfabaykal.spring.springboot_bootstrap.dao;


import com.alfabaykal.spring.springboot_bootstrap.model.Role;

public interface RoleDao {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
