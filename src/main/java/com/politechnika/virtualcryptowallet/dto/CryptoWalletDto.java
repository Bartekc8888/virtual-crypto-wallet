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

    public void getWallets(List<Wallet> wallets) {
          wallets.get(0).setAmount(this.bitcoin);
          wallets.get(1).setAmount(this.etherum);
          wallets.get(2).setAmount(this.xrp);
          wallets.get(3).setAmount(this.litecoin);
          wallets.get(4).setAmount(this.bitcoincash);
          wallets.get(5).setAmount(this.eos);
          wallets.get(6).setAmount(this.binancecoin);
          wallets.get(7).setAmount(this.bitcoinsv);
          wallets.get(8).setAmount(this.tether);
          wallets.get(9).setAmount(this.tron);
    }
}
