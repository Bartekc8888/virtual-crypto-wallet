package com.politechnika.virtualcryptowallet.cryptomarket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Platform implements Serializable {
    private Long id;
    private String name;
    private String symbol;
    private String slug;
    private String toke_address;
}
