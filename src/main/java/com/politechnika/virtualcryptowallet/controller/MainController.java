package com.politechnika.virtualcryptowallet.controller;

import com.politechnika.virtualcryptowallet.dto.RegistrationUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/dashboard"})
    public String root() {
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new RegistrationUserDto());
        return "register";
    }
}
