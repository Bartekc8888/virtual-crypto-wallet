package com.politechnika.virtualcryptowallet.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "auth_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    private boolean enabled;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
