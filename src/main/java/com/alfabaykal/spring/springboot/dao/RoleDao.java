package com.alfabaykal.spring.springboot.dao;


import com.alfabaykal.spring.springboot.model.Role;

public interface RoleDao {
    Role getRoleById(Long id);
    Role getRoleByName(String name);
}
