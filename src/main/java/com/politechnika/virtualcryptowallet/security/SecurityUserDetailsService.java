package com.politechnika.virtualcryptowallet.security;

import com.politechnika.virtualcryptowallet.dto.RegistrationUserDto;
import com.politechnika.virtualcryptowallet.model.User;
import com.politechnika.virtualcryptowallet.model.UserRole;
import com.politechnika.virtualcryptowallet.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new SecurityUserDetails(user);
    }

    public User registerUser(RegistrationUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setUserRole(UserRole.USER);
        return userRepository.save(user);
    }
}