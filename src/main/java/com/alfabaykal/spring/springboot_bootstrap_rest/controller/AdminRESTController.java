package com.alfabaykal.spring.springboot_bootstrap_rest.controller;

import com.alfabaykal.spring.springboot_bootstrap_rest.exception_handling.RoleDoesNotExistException;
import com.alfabaykal.spring.springboot_bootstrap_rest.exception_handling.UserDoesNotExistException;
import com.alfabaykal.spring.springboot_bootstrap_rest.model.Role;
import com.alfabaykal.spring.springboot_bootstrap_rest.model.User;
import com.alfabaykal.spring.springboot_bootstrap_rest.service.RoleService;
import com.alfabaykal.spring.springboot_bootstrap_rest.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminRESTController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new UserDoesNotExistException("User with ID = " + id + " does not exist");
        }
        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user/*, @ModelAttribute("my_role") String my_role*/) {
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleService.getRoleByName("ROLE_" + my_role));
//        roles.add(roleService.getRoleByName("ROLE_USER"));
//        user.setRoles(roles);
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new UserDoesNotExistException("User with ID = " + id + " does not exist");
        }
        userService.deleteUser(id);

        return "User with ID = " + id + " was deleted.";
    }

    @GetMapping("/roles")
        public List<Role> getRoles() {
        return roleService.getAllRoles();
        }

    @GetMapping("/roles/{id}")
    public Role getRole(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            throw new RoleDoesNotExistException("Role with ID = " + id + " does not exist");
        }
        return roleService.getRoleById(id);
    }
}
