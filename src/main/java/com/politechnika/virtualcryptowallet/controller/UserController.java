package com.politechnika.virtualcryptowallet.controller;


import javax.validation.Valid;

import com.politechnika.virtualcryptowallet.dto.RegistrationUserDto;
import com.politechnika.virtualcryptowallet.model.User;
import com.politechnika.virtualcryptowallet.security.SecurityUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private SecurityUserDetailsService userDetailsService;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid RegistrationUserDto userDto,
                               BindingResult result) {
        User user = userDetailsService.registerUser(userDto);

        return "redirect:/login";
    }
}
