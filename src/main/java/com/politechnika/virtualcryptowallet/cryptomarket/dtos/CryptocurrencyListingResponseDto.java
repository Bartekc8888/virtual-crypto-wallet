package com.politechnika.virtualcryptowallet.cryptomarket.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptocurrencyListingResponseDto implements Serializable {
    private List<CryptoDataDto> data;
    private ResponseStatusDto status;
}

