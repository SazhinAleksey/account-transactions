package com.example.accounttransactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferDto {
    private String nameFrom;
    private String nameTo;
    private BigDecimal amount;
}