package com.alfabaykal.spring.springboot_bootstrap.controller;

import com.alfabaykal.spring.springboot_bootstrap.model.Role;
import com.alfabaykal.spring.springboot_bootstrap.model.User;
import com.alfabaykal.spring.springboot_bootstrap.service.RoleService;
import com.alfabaykal.spring.springboot_bootstrap.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final HttpServletResponse response;
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(HttpServletResponse response, UserService userService, RoleService roleService) {
        this.response = response;
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
    public String addUser(@ModelAttribute("user") User user, @ModelAttribute("my_role") String my_role) throws IOException {
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_" + my_role));
            roles.add(roleService.getRoleByName("ROLE_USER"));
            user.setRoles(roles);
            userService.addUser(user);
        } catch (DataAccessException e) {
            response.sendError(400, "Role does not exist");
            return "admin";
        }
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id, @ModelAttribute("my_role") String my_role) throws IOException {
        try {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_" + my_role));
            roles.add(roleService.getRoleByName("ROLE_USER"));
            user.setRoles(roles);
            userService.updateUser(id, user);
        } catch (DataAccessException e) {
            response.sendError(400, "Role does not exist");
            return "admin";
        }
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}