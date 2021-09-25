package com.alfabaykal.spring.springboot_bootstrap_rest.service;


import com.alfabaykal.spring.springboot_bootstrap_rest.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
}
