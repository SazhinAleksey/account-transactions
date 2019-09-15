package com.example.accounttransactions.service;

import com.example.accounttransactions.dto.AccountDto;
import com.example.accounttransactions.dto.TransferDto;
import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.exception.AccountTransactionException;
import com.example.accounttransactions.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public void depositMoney(AccountDto accountDto) throws AccountTransactionException {
        Account account = getAccountByName(accountDto.getName());
        BigDecimal newBalance = account.getBalance().add(accountDto.getAmount());
        setBalance(account, newBalance);
    }

    @Transactional
    public void withdrawMoney(AccountDto accountDto) throws AccountTransactionException {
        Account account = getAccountByName(accountDto.getName());
        BigDecimal newBalance = account.getBalance().add(accountDto.getAmount().negate());
        setBalance(account, newBalance);
    }

    @Transactional
    public void transferMoney(TransferDto transferDto) throws AccountTransactionException {
        Account accountFrom = getAccountByName(transferDto.getNameFrom());
        Account accountTo = getAccountByName(transferDto.getNameTo());
        BigDecimal amount = transferDto.getAmount();
        BigDecimal withdrawAmount = accountFrom.getBalance().add(amount.negate());
        BigDecimal depositAmount = accountTo.getBalance().add(amount);
        setBalance(accountFrom, withdrawAmount);
        setBalance(accountTo, depositAmount);
    }

    private Account getAccountByName(String accountName) {
        Account account = accountRepository.findAccountByName(accountName);
        if (account != null) {
            return account;
        } else {
            throw new AccountTransactionException(String.format("No such account: %s", accountName));
        }
    }

    private void setBalance(Account account, BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
            account.setBalance(balance);
        } else {
            throw new AccountTransactionException(String.format("Not enough money on the account: %s.", account.getName()));
        }
    }
}