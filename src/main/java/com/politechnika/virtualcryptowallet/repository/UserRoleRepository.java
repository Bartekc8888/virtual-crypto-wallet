package com.politechnika.virtualcryptowallet.repository;

import com.politechnika.virtualcryptowallet.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByUsername(String username);
}
