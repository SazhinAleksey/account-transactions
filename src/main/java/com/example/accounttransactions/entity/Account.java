package com.example.accounttransactions.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String fullName;
    @Getter
    @Setter
    private Long balance;

    public Account(String fullName, Long balance) {
        this.fullName = fullName;
        this.balance = balance;
    }
}