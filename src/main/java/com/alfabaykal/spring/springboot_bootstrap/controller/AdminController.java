package com.alfabaykal.spring.springboot_bootstrap.controller;

import com.alfabaykal.spring.springboot_bootstrap.exception_handling.RoleDoesNotExist;
import com.alfabaykal.spring.springboot_bootstrap.exception_handling.RoleDoesNotExistException;
import com.alfabaykal.spring.springboot_bootstrap.model.Role;
import com.alfabaykal.spring.springboot_bootstrap.model.User;
import com.alfabaykal.spring.springboot_bootstrap.service.RoleService;
import com.alfabaykal.spring.springboot_bootstrap.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUsers(ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("user", userService.getUser(principal.getName()));
        return "admin";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user, @ModelAttribute("my_role") String my_role) {
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_" + my_role));
            roles.add(roleService.getRoleByName("ROLE_USER"));
            user.setRoles(roles);
            userService.addUser(user);
        } catch (DataAccessException e) {
            throw new RoleDoesNotExistException("Role " + my_role + " does not exist");
        }
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id, @ModelAttribute("my_role") String my_role){
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_" + my_role));
            roles.add(roleService.getRoleByName("ROLE_USER"));
            user.setRoles(roles);
            userService.updateUser(id, user);
        } catch (DataAccessException e) {
            throw new RoleDoesNotExistException("Role " + my_role + " does not exist");
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @ExceptionHandler
    public ResponseEntity<RoleDoesNotExist> handleException (RoleDoesNotExistException e) {
        RoleDoesNotExist data = new RoleDoesNotExist();
        data.setInfo(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}