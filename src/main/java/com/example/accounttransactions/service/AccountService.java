package com.example.accounttransactions.service;

import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.exception.AccountTransactionException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AccountService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insert(Account account) {
        entityManager.persist(account);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void topUpBalance(String fullName, Long amount) throws AccountTransactionException {
        Account account = (Account) entityManager.createQuery("select a from Account a where a.fullName = :value")
                .setParameter("value", fullName).getSingleResult();
        if (account == null) {
            throw new AccountTransactionException(String.format("No such account: %s", fullName));
        }
        Long newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new AccountTransactionException(String.format("Not enough money on the account: %s. Balance is: %d", account.getFullName(), account.getBalance()));
        }
        account.setBalance(newBalance);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = AccountTransactionException.class)
    public void transferMoney(String fullNameFrom, String fullNameTo, Long amount) throws AccountTransactionException {
        topUpBalance(fullNameFrom, -amount);
        topUpBalance(fullNameTo, amount);
    }
}