package com.alfabaykal.spring.springboot_bootstrap_rest.controller;

import com.alfabaykal.spring.springboot_bootstrap_rest.exception_handling.UserDoesNotExistException;
import com.alfabaykal.spring.springboot_bootstrap_rest.model.User;
import com.alfabaykal.spring.springboot_bootstrap_rest.service.RoleService;
import com.alfabaykal.spring.springboot_bootstrap_rest.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public List<User> getUsers() {
        List<User> allUsers = userService.getAllUser();

        return allUsers;
    }

    @GetMapping("/admin/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.getUser(id);

        if (user == null) {
            throw new UserDoesNotExistException("User with ID = " + id + " does not exist");
        }

        return user;
    }

    @PostMapping("/admin")
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @PutMapping("/admin")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("admin/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new UserDoesNotExistException("User with ID = " + id + " does not exist");
        }
        userService.deleteUser(id);

        return "User with ID = " + id + " was deleted.";
    }
}
