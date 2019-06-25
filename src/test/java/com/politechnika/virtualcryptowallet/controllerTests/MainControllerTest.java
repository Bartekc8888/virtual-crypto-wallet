package com.politechnika.virtualcryptowallet.controllerTests;

import com.politechnika.virtualcryptowallet.controller.MainController;
import com.politechnika.virtualcryptowallet.cryptomarket.CryptoApiRequestService;
import com.politechnika.virtualcryptowallet.cryptomarket.dtos.CryptoDataDto;
import com.politechnika.virtualcryptowallet.cryptomarket.dtos.CryptocurrencyListingResponseDto;
import com.politechnika.virtualcryptowallet.cryptomarket.dtos.CurrencyPriceStatisticsDto;
import com.politechnika.virtualcryptowallet.dto.CryptoWalletDto;
import com.politechnika.virtualcryptowallet.security.SecurityCryptoWalletService;
import org.jboss.jandex.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class MainControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    WebApplicationContext wAC;

    @MockBean
    CryptoApiRequestService cryptoApiRequestService;

    @MockBean
    SecurityCryptoWalletService securityCryptoWalletService;

    @Mock
    MainController mainController;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wAC).build();
    }

    @Test
    public void getRootTest() throws Exception {
        CurrencyPriceStatisticsDto currencyPriceStatisticsDto = new CurrencyPriceStatisticsDto();
        currencyPriceStatisticsDto.setPrice(55d);

        Map<String, CurrencyPriceStatisticsDto> map = new HashMap<>();
        map.put("USD", currencyPriceStatisticsDto);

        CryptoDataDto cryptoDataDto = new CryptoDataDto();
        cryptoDataDto.setQuote(map);

        List<CryptoDataDto> list = new ArrayList<>();
        list.add(cryptoDataDto);

        CryptocurrencyListingResponseDto currentBitcoinValue = new CryptocurrencyListingResponseDto();
        currentBitcoinValue.setData(list);

        CryptoWalletDto cryptoWalletDto = new CryptoWalletDto();

        Mockito.when(securityCryptoWalletService.loadValues()).thenReturn(cryptoWalletDto);

        Mockito.when(mainController.getValue(Mockito.any())).thenReturn(5d);


        Mockito.when(cryptoApiRequestService.getCurrentBitcoinValue()).thenReturn(currentBitcoinValue);
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void getLogin() throws Exception {
        mvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    public void getRegister() throws Exception {
        mvc.perform(get("/register")).andExpect(status().isOk());
    }
}
