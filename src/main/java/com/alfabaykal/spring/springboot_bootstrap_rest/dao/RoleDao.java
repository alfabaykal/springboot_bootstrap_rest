package com.alfabaykal.spring.springboot_bootstrap_rest.dao;


import com.alfabaykal.spring.springboot_bootstrap_rest.model.Role;

public interface RoleDao {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
