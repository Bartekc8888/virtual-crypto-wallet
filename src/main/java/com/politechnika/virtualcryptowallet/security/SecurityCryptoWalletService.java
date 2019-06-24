package com.politechnika.virtualcryptowallet.security;

import com.politechnika.virtualcryptowallet.dto.CryptoWalletDto;
import com.politechnika.virtualcryptowallet.model.Wallet;
import com.politechnika.virtualcryptowallet.repository.CryptoWalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SecurityCryptoWalletService {

    private CryptoWalletRepository cryptoWalletRepository;

    public CryptoWalletDto loadValues(){
        List<Wallet> wallets = cryptoWalletRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return new CryptoWalletDto(wallets);
    }

    public void saveValues(CryptoWalletDto cryptoWalletDto) {
        List<Wallet> wallets = cryptoWalletDto.getWallets(SecurityContextHolder.getContext().getAuthentication().getName());
        cryptoWalletRepository.saveAll(wallets);
    }
}
