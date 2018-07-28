package com.example.accounttransactions.runner;

import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) {
        log.info("Start app. Adding accounts...");
        accountService.insert(new Account("IvanovIvan", 0L));
        accountService.insert(new Account("PetrovPetr", 1000L));
        log.info("Add successful!");
    }
}