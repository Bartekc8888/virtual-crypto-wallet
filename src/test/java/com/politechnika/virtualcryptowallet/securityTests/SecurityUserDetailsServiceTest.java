package com.politechnika.virtualcryptowallet.securityTests;

import com.politechnika.virtualcryptowallet.dto.RegistrationUserDto;
import com.politechnika.virtualcryptowallet.model.User;
import com.politechnika.virtualcryptowallet.model.UserRole;
import com.politechnika.virtualcryptowallet.model.UserRoleType;
import com.politechnika.virtualcryptowallet.repository.UserRepository;
import com.politechnika.virtualcryptowallet.repository.UserRoleRepository;
import com.politechnika.virtualcryptowallet.security.SecurityCryptoWalletService;
import com.politechnika.virtualcryptowallet.security.SecurityUserDetails;
import com.politechnika.virtualcryptowallet.security.SecurityUserDetailsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class SecurityUserDetailsServiceTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserRoleRepository userRoleRepository;

    @Mock
    SecurityCryptoWalletService securityCryptoWalletService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    public void loadUserByUsernameTest(){

        User user = new User();
        user.setUsername("adminTest");
        user.setPassword("adminTest");

        Mockito.when(userRepository.findByUsername(Matchers.eq("adminTest"))).thenReturn(user);

        SecurityUserDetailsService securityUserDetailsService = new SecurityUserDetailsService(userRepository, userRoleRepository, passwordEncoder);

        UserDetails userDetails = securityUserDetailsService.loadUserByUsername("adminTest");

        Assert.assertEquals(userDetails.getUsername(), "adminTest");

    }

    @Test
    public void registerUserTest(){

        User user = new User();
        user.setUsername("adminTest");
        user.setPassword("adminTest");


        RegistrationUserDto registrationUserDto = new RegistrationUserDto();
        registrationUserDto.setFirstName("adminTest");
        registrationUserDto.setUsername("adminTest");
        registrationUserDto.setPassword("adminPassword");
        registrationUserDto.setMatchingPassword("adminPassword");

        Mockito.when(userRepository.findByUsername(Matchers.eq("adminTest"))).thenReturn(null);
        Mockito.when(passwordEncoder.encode(Matchers.anyString())).thenReturn("adminPassword");
        Mockito.when(userRepository.save(Matchers.any())).thenReturn(user);

        SecurityUserDetailsService securityUserDetailsService = new SecurityUserDetailsService(userRepository, userRoleRepository, passwordEncoder);

        User saved = securityUserDetailsService.registerUser(registrationUserDto);

        Assert.assertEquals(saved.getUsername(), "adminTest");

    }
}
