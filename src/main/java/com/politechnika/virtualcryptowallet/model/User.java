package com.politechnika.virtualcryptowallet.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Column(name = "firstName")
    private String firstName;

    private boolean enabled;

    @Column(name = "userRole")
    private UserRole userRole;
}
