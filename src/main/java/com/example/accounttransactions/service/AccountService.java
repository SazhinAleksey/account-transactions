package com.example.accounttransactions.service;

import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.exception.AccountTransactionException;
import com.example.accounttransactions.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public void insert(Account account) {
        accountRepository.saveAndFlush(account);
    }

    public void changeBalance(String name, BigDecimal amount) throws AccountTransactionException {
        Account account = accountRepository.findAccountByName(name);
        if (account == null) {
            throw new AccountTransactionException(String.format("No such account: %s", name));
        }
        BigDecimal newBalance = account.getBalance().add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new AccountTransactionException(String.format("Not enough money on the account: %s.", account.getName()));
        }
        account.setBalance(newBalance);
    }

    public void transferMoney(String nameFrom, String nameTo, BigDecimal amount) throws AccountTransactionException {
        changeBalance(nameFrom, amount.negate());
        changeBalance(nameTo, amount);
    }
}