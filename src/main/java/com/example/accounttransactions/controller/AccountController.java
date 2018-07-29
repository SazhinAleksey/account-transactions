package com.example.accounttransactions.controller;

import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping("/put")
    public ResponseEntity<?> put(@RequestParam String name, @RequestParam BigDecimal amount) {
        accountService.changeBalance(name, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam String name, @RequestParam BigDecimal amount) {
        accountService.changeBalance(name, amount.negate());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/transfer")
    @ResponseBody
    public ResponseEntity<?> transfer(@RequestParam String nameFrom, @RequestParam String nameTo, @RequestParam BigDecimal amount) {
        accountService.transferMoney(nameFrom, nameTo, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}