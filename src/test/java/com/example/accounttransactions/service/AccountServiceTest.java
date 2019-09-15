package com.example.accounttransactions.service;

import com.example.accounttransactions.dto.AccountDto;
import com.example.accounttransactions.dto.TransferDto;
import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.exception.AccountTransactionException;
import com.example.accounttransactions.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService accountService;

    @Test
    public void test_getAccounts_success() {
        accountService.getAccounts();

        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void test_depositMoney_success() {
        String name = "Name";
        BigDecimal amount = new BigDecimal(1);
        when(accountRepository.findAccountByName(name)).thenReturn(new Account(name, amount));

        accountService.depositMoney(new AccountDto(name, amount));

        verify(accountRepository, times(1)).findAccountByName(name);
    }

    @Test
    public void test_withdrawMoney_success() {
        String name = "Name";
        BigDecimal amount = new BigDecimal(1);
        when(accountRepository.findAccountByName(name)).thenReturn(new Account(name, amount));

        accountService.withdrawMoney(new AccountDto(name, amount));

        verify(accountRepository, times(1)).findAccountByName(name);
    }

    @Test
    public void test_transferMoney_success() {
        String nameFrom = "NameFrom";
        String nameTo = "NameTo";
        BigDecimal amount = new BigDecimal(1);
        when(accountRepository.findAccountByName(nameFrom)).thenReturn(new Account(nameFrom, amount));
        when(accountRepository.findAccountByName(nameTo)).thenReturn(new Account(nameTo, amount));

        accountService.transferMoney(new TransferDto(nameFrom, nameTo, amount));

        verify(accountRepository, times(1)).findAccountByName(nameFrom);
        verify(accountRepository, times(1)).findAccountByName(nameTo);
    }

    @Test(expected = AccountTransactionException.class)
    public void test_depositMoney_throwExc() {
        String name = "Name";
        BigDecimal amount = new BigDecimal(1);
        when(accountRepository.findAccountByName(name)).thenReturn(null);

        accountService.depositMoney(new AccountDto(name, amount));

        verify(accountRepository, times(1)).findAccountByName(name);
    }

    @Test(expected = AccountTransactionException.class)
    public void test_withdrawMoney_throwExc() {
        String name = "Name";
        BigDecimal amount = new BigDecimal(1);
        when(accountRepository.findAccountByName(name)).thenReturn(new Account(name, new BigDecimal(0)));

        accountService.withdrawMoney(new AccountDto(name, amount));

        verify(accountRepository, times(1)).findAccountByName(name);
    }
}