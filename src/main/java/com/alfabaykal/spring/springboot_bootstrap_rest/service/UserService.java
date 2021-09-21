
package com.alfabaykal.spring.springboot_bootstrap_rest.service;


import com.alfabaykal.spring.springboot_bootstrap_rest.model.User;

import java.util.List;

public interface UserService {
    void addUser(User employee);
    List<User> getAllUser();
    User getUser(int id);
    User getUser(String username);
    void updateUser(User user);
    void deleteUser(int id);
}