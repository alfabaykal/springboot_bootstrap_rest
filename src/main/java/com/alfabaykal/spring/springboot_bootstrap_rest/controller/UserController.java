package com.alfabaykal.spring.springboot_bootstrap_rest.controller;

import com.alfabaykal.spring.springboot_bootstrap_rest.model.User;
import com.alfabaykal.spring.springboot_bootstrap_rest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUser(ModelMap model, Principal principal) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}