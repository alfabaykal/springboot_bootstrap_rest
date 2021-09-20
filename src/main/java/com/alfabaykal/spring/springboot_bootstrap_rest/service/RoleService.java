package com.alfabaykal.spring.springboot_bootstrap_rest.service;


import com.alfabaykal.spring.springboot_bootstrap_rest.model.Role;

public interface RoleService {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
