package com.alfabaykal.spring.springboot_bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {

//    @GetMapping("/")
//    public String getHomePage() {
//        return "index";
//    }

//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "new";
//    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}