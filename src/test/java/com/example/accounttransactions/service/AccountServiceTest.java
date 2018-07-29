package com.example.accounttransactions.service;

import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.exception.AccountTransactionException;
import com.example.accounttransactions.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void insertOk() {
        Account accountExpected = new Account("Test", new BigDecimal(100));
        accountService.insert(accountExpected);
        Account accountFromDb = accountRepository.findAccountByName(accountExpected.getName());
        assertThat(accountFromDb).isEqualTo(accountExpected);
    }

    @Test
    public void changeBalancePositiveOk() {
        Account accountExpected = new Account("Test", new BigDecimal(100));
        accountService.insert(accountExpected);
        accountService.changeBalance(accountExpected.getName(), new BigDecimal(101.22));
        Account accountFromDb = accountRepository.findAccountByName(accountExpected.getName());
        assertThat(accountFromDb.getBalance()).isEqualTo(new BigDecimal(201.22));
    }

    @Test
    public void changeBalanceNegativeOk() {
        Account accountExpected = new Account("Test", new BigDecimal(100));
        accountService.insert(accountExpected);
        accountService.changeBalance(accountExpected.getName(), new BigDecimal(99).negate());
        Account accountFromDb = accountRepository.findAccountByName(accountExpected.getName());
        assertThat(accountFromDb.getBalance()).isEqualTo(new BigDecimal(1));
    }

    @Test(expected = AccountTransactionException.class)
    public void changeBalanceNoSuchAccountExc() {
        Account accountExpected = new Account("Test", new BigDecimal(100));
        accountService.changeBalance(accountExpected.getName(), new BigDecimal(101.22));
    }

    @Test(expected = AccountTransactionException.class)
    public void changeBalanceNotEnoughMoneyExc() {
        Account accountExpected = new Account("Test", new BigDecimal(100));
        accountService.insert(accountExpected);
        accountService.changeBalance(accountExpected.getName(), new BigDecimal(-101.22));
    }

    @Test
    public void transferMoney() {
        Account accountExpectedFrom = new Account("Test1", new BigDecimal(100));
        Account accountExpectedTo = new Account("Test2", new BigDecimal(100));
        accountService.insert(accountExpectedFrom);
        accountService.insert(accountExpectedTo);
        accountService.transferMoney(accountExpectedFrom.getName(), accountExpectedTo.getName(), new BigDecimal(100));
        Account accountFromDbFrom = accountRepository.findAccountByName(accountExpectedFrom.getName());
        Account accountFromDbTo = accountRepository.findAccountByName(accountExpectedTo.getName());
        assertThat(accountFromDbFrom.getBalance()).isEqualTo(new BigDecimal(0));
        assertThat(accountFromDbTo.getBalance()).isEqualTo(new BigDecimal(200));
    }
}