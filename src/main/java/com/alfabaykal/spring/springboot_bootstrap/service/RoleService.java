package com.alfabaykal.spring.springboot_bootstrap.service;


import com.alfabaykal.spring.springboot_bootstrap.model.Role;

public interface RoleService {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
