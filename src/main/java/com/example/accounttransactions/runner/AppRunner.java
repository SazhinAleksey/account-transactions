package com.example.accounttransactions.runner;

import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

    private final AccountRepository accountRepository;
    private final EntityManager entityManager;


    public AppRunner(AccountRepository accountRepository, EntityManager entityManager) {
        this.accountRepository = accountRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) {
        log.info("Start DB configuration...");
        entityManager.createNativeQuery("CREATE SEQUENCE IF NOT EXISTS account_seq").executeUpdate();
        accountRepository.saveAndFlush(new Account("Ivan", new BigDecimal(0)));
        accountRepository.saveAndFlush(new Account("Petr", new BigDecimal(1000)));
        accountRepository.saveAndFlush(new Account("Misha", new BigDecimal(100.1)));
        log.info("DB configuration is finished successfully!");
    }
}