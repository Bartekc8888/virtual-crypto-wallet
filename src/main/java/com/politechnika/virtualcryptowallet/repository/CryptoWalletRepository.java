package com.politechnika.virtualcryptowallet.repository;

import com.politechnika.virtualcryptowallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CryptoWalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByUsername(String username);
}
