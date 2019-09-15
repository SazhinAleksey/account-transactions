package com.example.accounttransactions.controller;

import com.example.accounttransactions.dto.AccountDto;
import com.example.accounttransactions.dto.TransferDto;
import com.example.accounttransactions.entity.Account;
import com.example.accounttransactions.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @PutMapping("/deposit")
    public ResponseEntity<?> depositMoney(@RequestBody AccountDto accountDto) {
        accountService.depositMoney(accountDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<?> withdrawMoney(@RequestBody AccountDto accountDto) {
        accountService.withdrawMoney(accountDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransferDto transferDto) {
        accountService.transferMoney(transferDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}