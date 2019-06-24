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
    private List<Wallet> wallets;

    public CryptoWalletDto loadValues(){
        wallets = cryptoWalletRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(wallets.size()==0)
            initWallets(SecurityContextHolder.getContext().getAuthentication().getName());
        return new CryptoWalletDto(wallets);
    }

    private void initWallets(String username) {
        wallets.add(new Wallet(username,"bitcoin",0.0));
        wallets.add(new Wallet(username,"etherum",0.0));
        wallets.add(new Wallet(username,"xrp",0.0));
        wallets.add(new Wallet(username,"litecoin",0.0));
        wallets.add(new Wallet(username,"bitcoincash",0.0));
        wallets.add(new Wallet(username,"eos",0.0));
        wallets.add(new Wallet(username,"binancecoin",0.0));
        wallets.add(new Wallet(username,"bitcoinsv",0.0));
        wallets.add(new Wallet(username,"tether",0.0));
        wallets.add(new Wallet(username,"tron",0.0));
    }

    public void saveValues(CryptoWalletDto cryptoWalletDto) {
        cryptoWalletDto.getWallets(wallets);
        cryptoWalletRepository.saveAll(wallets);
    }
}
