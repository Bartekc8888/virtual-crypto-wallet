package com.politechnika.virtualcryptowallet.controller;

import com.politechnika.virtualcryptowallet.cryptomarket.CryptoApiRequestService;
import com.politechnika.virtualcryptowallet.cryptomarket.dtos.CryptocurrencyListingResponseDto;
import com.politechnika.virtualcryptowallet.cryptomarket.dtos.CurrencyPriceStatisticsDto;
import com.politechnika.virtualcryptowallet.dto.RegistrationUserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {

    private CryptoApiRequestService cryptoApiRequestService;

    @GetMapping({"/", "/dashboard"})
    public String root(Model model) {
        String bitcoinPrice = getBitcoinPrice();

        model.addAttribute("bitcoinPrice", bitcoinPrice);
        return "dashboard";
    }

    private String getBitcoinPrice() {
        String currency = "USD";

        CryptocurrencyListingResponseDto currentBitcoinValue = cryptoApiRequestService.getCurrentBitcoinValue();
        Long currentPrice = currentBitcoinValue.getData().stream()
                                               .findAny()
                                               .map(cryptoDataDto -> cryptoDataDto.getQuote().get(currency))
                                               .map(CurrencyPriceStatisticsDto::getPrice)
                                               .orElse(-1L);

        return currentPrice + " " + currency;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new RegistrationUserDto());
        return "register";
    }
}
