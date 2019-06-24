package com.politechnika.virtualcryptowallet.dto;

import com.politechnika.virtualcryptowallet.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoWalletDto {

    private Double bitcoin = 0.d;

    private Double etherum = 0.d;

    private Double xrp = 0.d;

    private Double litecoin = 0.d;

    private Double bitcoincash = 0.d;

    private Double eos = 0.d;

    private Double binancecoin = 0.d;

    private Double bitcoinsv = 0.d;

    private Double tether = 0.d;

    private Double tron = 0.d;

    public CryptoWalletDto(List<Wallet> walletList){
        for(Wallet wallet:walletList){
            switch (wallet.getCurrency()){
                case "bitcoin":
                    bitcoin = wallet.getAmount();
                    break;
                case "etherum":
                    etherum = wallet.getAmount();
                    break;
                case "xrp":
                    xrp = wallet.getAmount();
                    break;
                case "litecoin":
                    litecoin = wallet.getAmount();
                    break;
                case "bitcoincash":
                    bitcoincash = wallet.getAmount();
                    break;
                case "eos":
                    eos = wallet.getAmount();
                    break;
                case "binancecoin":
                    binancecoin = wallet.getAmount();
                    break;
                case "bitcoinsv":
                    bitcoinsv = wallet.getAmount();
                    break;
                case "tether":
                    tether = wallet.getAmount();
                    break;
                case "tron":
                    tron = wallet.getAmount();
                    break;
            }
        }
    }

    public List<Wallet> getWallets(String username) {
        List<Wallet> wallets = new ArrayList<>();
        wallets.add(new Wallet(username,"bitcoin",this.bitcoin));
        wallets.add(new Wallet(username,"etherum",this.etherum));
        wallets.add(new Wallet(username,"xrp",this.xrp));
        wallets.add(new Wallet(username,"litecoin",this.litecoin));
        wallets.add(new Wallet(username,"bitcoincash",this.bitcoincash));
        wallets.add(new Wallet(username,"eos",this.eos));
        wallets.add(new Wallet(username,"binancecoin",this.binancecoin));
        wallets.add(new Wallet(username,"bitcoinsv",this.bitcoinsv));
        wallets.add(new Wallet(username,"tether",this.tether));
        wallets.add(new Wallet(username,"tron",this.tron));
        return wallets;
    }
}
