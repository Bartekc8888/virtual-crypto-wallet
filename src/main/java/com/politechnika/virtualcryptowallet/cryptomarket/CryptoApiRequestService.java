package com.politechnika.virtualcryptowallet.cryptomarket;

import java.util.Collections;

import com.politechnika.virtualcryptowallet.config.CacheConfig;
import com.politechnika.virtualcryptowallet.cryptomarket.dtos.CryptocurrencyListingResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class CryptoApiRequestService {
    @Value("${api.key}")
    private String apiKey;

    @Value("${api.key.header}")
    private String apiKeyHeader;

    @Value("${api.url}")
    private String apiUrl;

    private RestTemplate restTemplate;

    public CryptoApiRequestService(RestTemplateBuilder restTemplateBuilder) {
        restTemplateBuilder.rootUri(apiUrl);
        restTemplate = restTemplateBuilder.build();
    }

    @Cacheable(CacheConfig.BITCOIN_CACHE)
    public CryptocurrencyListingResponseDto getCurrentBitcoinValue() {
        return getValue(1);
    }

    @Cacheable(CacheConfig.ETHERUM_CACHE)
    public CryptocurrencyListingResponseDto getCurrentEtherumValue() {
        return getValue(2);
    }

    @Cacheable(CacheConfig.XRP_CACHE)
    public CryptocurrencyListingResponseDto getCurrentXRPValue() {
        return getValue(3);
    }

    @Cacheable(CacheConfig.LITECOIN_CACHE)
    public CryptocurrencyListingResponseDto getCurrentLitecoinValue() {
        return getValue(4);
    }

    @Cacheable(CacheConfig.BITCOINCASH_CACHE)
    public CryptocurrencyListingResponseDto getCurrentBitcoinCashValue() {
        return getValue(5);
    }

    @Cacheable(CacheConfig.EOS_CACHE)
    public CryptocurrencyListingResponseDto getCurrentEOSValue() {
        return getValue(6);
    }

    @Cacheable(CacheConfig.BINANCECOIN_CACHE)
    public CryptocurrencyListingResponseDto getCurrentBinanceCoinValue() {
        return getValue(7);
    }

    @Cacheable(CacheConfig.BITCOINSV_CACHE)
    public CryptocurrencyListingResponseDto getCurrentBitcoinSVValue() {
        return getValue(8);
    }

    @Cacheable(CacheConfig.TETHER_CACHE)
    public CryptocurrencyListingResponseDto getCurrentTetherValue() {
        return getValue(9);
    }

    @Cacheable(CacheConfig.TRON_CACHE)
    public CryptocurrencyListingResponseDto getCurrentTRONValue() {
        return getValue(10);
    }

    private CryptocurrencyListingResponseDto getValue(int start) {
        HttpHeaders headers = new HttpHeaders();
        addCommonHeaders(headers);

        String URL = apiUrl + "/v1/cryptocurrency/listings/latest";
        var builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("start", start)
                .queryParam("limit", 1)
                .queryParam("convert", "USD");

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<CryptocurrencyListingResponseDto> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, CryptocurrencyListingResponseDto.class);
        return exchange.getBody();
    }

    private void addCommonHeaders(HttpHeaders headers) {
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add(apiKeyHeader, apiKey);
    }
}
