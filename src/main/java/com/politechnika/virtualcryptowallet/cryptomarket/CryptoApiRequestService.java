package com.politechnika.virtualcryptowallet.cryptomarket;

import java.util.Collections;

import com.politechnika.virtualcryptowallet.cryptomarket.dtos.CryptocurrencyListingResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
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

    public CryptocurrencyListingResponseDto getCurrentBitcoinValue() {
        String url = apiUrl + "/v1/cryptocurrency/listings/latest";

        HttpHeaders headers = new HttpHeaders();
        addCommonHeaders(headers);

        var builder = UriComponentsBuilder.fromHttpUrl(url)
                                          .queryParam("start", 1)
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
