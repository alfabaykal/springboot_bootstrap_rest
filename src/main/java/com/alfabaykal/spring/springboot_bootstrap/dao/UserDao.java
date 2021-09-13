package com.alfabaykal.spring.springboot_bootstrap.dao;


import com.alfabaykal.spring.springboot_bootstrap.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User employee);
    List<User> getAllUser();
    User getUser(int id);
    void updateUser(int id, User user);
    void deleteUser(int id);
    User findByUsername(String username);
    User getUser(String username);
}