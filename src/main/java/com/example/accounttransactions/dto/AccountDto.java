package com.example.accounttransactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountDto {
    private String name;
    private BigDecimal amount;
}