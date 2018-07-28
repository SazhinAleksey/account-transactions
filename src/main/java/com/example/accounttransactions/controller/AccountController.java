package com.example.accounttransactions.controller;

import com.example.accounttransactions.exception.AccountTransactionException;
import com.example.accounttransactions.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/put")
    public void put(@RequestParam String fullName, @RequestParam Long amount) throws AccountTransactionException {
        accountService.topUpBalance(fullName, amount);
    }

    @RequestMapping("/withdraw")
    public void withdraw(@RequestParam String fullName, @RequestParam Long amount) throws AccountTransactionException {
        accountService.topUpBalance(fullName, -amount);
    }

    @RequestMapping("/transfer")
    @ResponseBody
    public void transfer(@RequestParam String fullNameFrom, @RequestParam String fullNameTo, @RequestParam Long amount) throws AccountTransactionException {
        accountService.transferMoney(fullNameFrom, fullNameTo, amount);
    }
}