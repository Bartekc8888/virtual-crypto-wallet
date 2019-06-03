package com.politechnika.virtualcryptowallet.dto;

import com.politechnika.virtualcryptowallet.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private UserRole userRole;
}
