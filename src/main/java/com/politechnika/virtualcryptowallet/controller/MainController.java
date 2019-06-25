package com.politechnika.virtualcryptowallet.controller;

import com.politechnika.virtualcryptowallet.cryptomarket.CryptoApiRequestService;
import com.politechnika.virtualcryptowallet.cryptomarket.dtos.CryptocurrencyListingResponseDto;
import com.politechnika.virtualcryptowallet.cryptomarket.dtos.CurrencyPriceStatisticsDto;
import com.politechnika.virtualcryptowallet.dto.CryptoWalletDto;
import com.politechnika.virtualcryptowallet.dto.RegistrationUserDto;
import com.politechnika.virtualcryptowallet.security.SecurityCryptoWalletService;
import com.politechnika.virtualcryptowallet.security.SecurityUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.valves.rewrite.Substitution;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@AllArgsConstructor
public class MainController {

    private CryptoApiRequestService cryptoApiRequestService;
    private SecurityCryptoWalletService cryptoWalletService;
    private final String currency = "USD";
    private final Map<String, Double> priceValues = new HashMap<>();
    private final Map<String, Double> cryptocurrenciesValues = new HashMap<>();

    @GetMapping({"/", "/dashboard"})
    public String root(Model model, @ModelAttribute("crypto") @Valid CryptoWalletDto cryptoWalletDto) {
        initPriceValues();
        model.addAllAttributes(priceValues);
        cryptoWalletDto = cryptoWalletService.loadValues();
        model.addAttribute("crypto",cryptoWalletDto);
        calculateValueOfCryptocurrencies(cryptoWalletDto);
        model.addAllAttributes(cryptocurrenciesValues);
        return "dashboard";
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

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("crypto") @Valid CryptoWalletDto cryptoWalletDto){
        cryptoWalletService.saveValues(cryptoWalletDto);
        model.addAllAttributes(priceValues);
        model.addAttribute("crypto",cryptoWalletDto);
        calculateValueOfCryptocurrencies(cryptoWalletDto);
        model.addAllAttributes(cryptocurrenciesValues);
        return "dashboard";
    }

    private void initPriceValues() {
        priceValues.put("bitcoinPrice",getValue(cryptoApiRequestService.getCurrentBitcoinValue()));
        priceValues.put("etherumPrice",getValue(cryptoApiRequestService.getCurrentEtherumValue()));
        priceValues.put("xrpPrice",getValue(cryptoApiRequestService.getCurrentXRPValue()));
        priceValues.put("litecoinPrice",getValue(cryptoApiRequestService.getCurrentLitecoinValue()));
        priceValues.put("bitcoincashPrice",getValue(cryptoApiRequestService.getCurrentBitcoinCashValue()));
        priceValues.put("eosPrice",getValue(cryptoApiRequestService.getCurrentEOSValue()));
        priceValues.put("binancecoinPrice",getValue(cryptoApiRequestService.getCurrentBinanceCoinValue()));
        priceValues.put("bitcoinsvpPrice",getValue(cryptoApiRequestService.getCurrentBitcoinSVValue()));
        priceValues.put("tetherPrice",getValue(cryptoApiRequestService.getCurrentTetherValue()));
        priceValues.put("tronPrice",getValue(cryptoApiRequestService.getCurrentTRONValue()));
    }

    private void calculateValueOfCryptocurrencies(CryptoWalletDto cryptoWalletDto) {
        cryptocurrenciesValues.put("bitcoinValue",cryptoWalletDto.getBitcoin()*priceValues.get("bitcoinPrice"));
        cryptocurrenciesValues.put("etherumValue",cryptoWalletDto.getEtherum()*priceValues.get("etherumPrice"));
        cryptocurrenciesValues.put("xrpValue",cryptoWalletDto.getXrp()*priceValues.get("xrpPrice"));
        cryptocurrenciesValues.put("litecoinValue",cryptoWalletDto.getLitecoin()*priceValues.get("litecoinPrice"));
        cryptocurrenciesValues.put("bitcoincashValue",cryptoWalletDto.getBitcoincash()*priceValues.get("bitcoincashPrice"));
        cryptocurrenciesValues.put("eosValue",cryptoWalletDto.getEos()*priceValues.get("eosPrice"));
        cryptocurrenciesValues.put("binancecoinValue",cryptoWalletDto.getBinancecoin()*priceValues.get("binancecoinPrice"));
        cryptocurrenciesValues.put("bitcoinsvpValue",cryptoWalletDto.getBitcoinsv()*priceValues.get("bitcoinsvpPrice"));
        cryptocurrenciesValues.put("tetherValue",cryptoWalletDto.getTether()*priceValues.get("tetherPrice"));
        cryptocurrenciesValues.put("tronValue",cryptoWalletDto.getTron()*priceValues.get("tronPrice"));
        cryptocurrenciesValues.put("sum",add());
    }

    private Double add() {
        Double sum = 0.d;
        for (Map.Entry<String,Double> value:cryptocurrenciesValues.entrySet()) {
            sum+=value.getValue();
        }
        return sum;
    }

    public Double getValue(CryptocurrencyListingResponseDto value) {
        Double currentPrice = value.getData().stream()
                .findAny()
                .map(cryptoDataDto -> cryptoDataDto.getQuote().get(currency))
                .map(CurrencyPriceStatisticsDto::getPrice)
                .orElse(-1.d);
        return Double.parseDouble(new DecimalFormat("0.00").format(currentPrice).replace(',','.'));
    }

}
