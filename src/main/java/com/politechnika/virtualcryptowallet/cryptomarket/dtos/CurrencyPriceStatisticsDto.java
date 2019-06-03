package com.politechnika.virtualcryptowallet.cryptomarket.dtos;

import java.io.Serializable;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPriceStatisticsDto implements Serializable {
    private Long price;
    private Long volume_24h;
    private Double percent_change_1h;
    private Double percent_change_24h;
    private Double percent_change_7d;
    private Long market_cap;
    private ZonedDateTime last_updated;
}
