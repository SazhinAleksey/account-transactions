package com.example.accounttransactions.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@NoArgsConstructor
public class Account {
    @Id
    @SequenceGenerator(name = "ACCOUNT_ID_GENERATOR", sequenceName = "account_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "ACCOUNT_ID_GENERATOR")
    @Getter
    private Long id;
    @Getter
    @Column(unique = true)
    private String name;
    @Getter
    @Setter
    private BigDecimal balance;

    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }
}