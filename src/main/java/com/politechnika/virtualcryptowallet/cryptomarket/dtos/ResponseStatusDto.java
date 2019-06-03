package com.politechnika.virtualcryptowallet.cryptomarket.dtos;

import java.io.Serializable;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatusDto implements Serializable {
    private ZonedDateTime timestamp;
    private Long error_code;
    private String error_message;
    private Long elapsed;
    private Long credit_count;
}
