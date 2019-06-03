package com.politechnika.virtualcryptowallet.cryptomarket.dtos;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoDataDto implements Serializable {
    private Long id;
    private String name;
    private String symbol;
    private String slug;
    private Long cmc_rank;
    private Long num_market_pairs;
    private Long circulating_supply;
    private Long total_supply;
    private Long max_supply;
    private ZonedDateTime last_updated;
    private ZonedDateTime  date_added;
    private List<String> tags;
    private String platform;
    private Map<String, CurrencyPriceStatisticsDto> quote;
}
