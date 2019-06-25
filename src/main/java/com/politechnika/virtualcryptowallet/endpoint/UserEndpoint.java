package com.politechnika.virtualcryptowallet.endpoint;

import com.politechnika.virtualcryptowallet.dto.RegistrationUserDto;
import com.politechnika.virtualcryptowallet.security.SecurityUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserEndpoint {
    private SecurityUserDetailsService userDetailsService;

    @PostMapping("/register")
    public void registerUser(@RequestBody RegistrationUserDto userDto) {
        userDetailsService.registerUser(userDto);
    }

}
