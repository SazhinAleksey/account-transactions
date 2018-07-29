package com.example.accounttransactions.runner;

import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    private final AccountService accountService;

    @Autowired
    public AppRunner(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) {
        log.info("Start app. Adding accounts...");
        accountService.insert(new Account("Ivan", new BigDecimal(0)));
        accountService.insert(new Account("Petr", new BigDecimal(1000)));
        accountService.insert(new Account("Misha", new BigDecimal(100.1)));
        log.info("Add successful!");
    }
}