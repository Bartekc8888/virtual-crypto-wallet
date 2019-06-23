package com.politechnika.virtualcryptowallet.security;

import com.politechnika.virtualcryptowallet.dto.RegistrationUserDto;
import com.politechnika.virtualcryptowallet.model.User;
import com.politechnika.virtualcryptowallet.model.UserRole;
import com.politechnika.virtualcryptowallet.model.UserRoleType;
import com.politechnika.virtualcryptowallet.repository.UserRepository;
import com.politechnika.virtualcryptowallet.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new SecurityUserDetails(user);
    }

    public User registerUser(RegistrationUserDto userDto) {
        User existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);
        User savedUser = userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setUsername(userDto.getUsername());
        userRole.setUserRoleType(UserRoleType.USER);
        userRoleRepository.save(userRole);

        return savedUser;
    }
}