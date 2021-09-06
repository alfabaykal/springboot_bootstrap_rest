package com.alfabaykal.spring.springboot.service;


import com.alfabaykal.spring.springboot.model.Role;

public interface RoleService {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
