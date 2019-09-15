package com.example.accounttransactions.controller;

import com.example.accounttransactions.dto.AccountDto;
import com.example.accounttransactions.dto.TransferDto;
import com.example.accounttransactions.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @MockBean
    private AccountService accountService;
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAccounts() throws Exception {
        mvc.perform(get("/accounts"));
        verify(accountService, times(1)).getAccounts();
    }

    @Test
    public void deposit() throws Exception {
        AccountDto accountDto = new AccountDto("Sasha", new BigDecimal(100));
        mvc.perform(
                put("/deposit")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getAccountJson(accountDto.getName(), accountDto.getAmount()))
        ).andExpect(status().isOk());
        verify(accountService, times(1)).depositMoney(accountDto);
    }

    @Test
    public void withdraw() throws Exception {
        AccountDto accountDto = new AccountDto("Sasha", new BigDecimal(100));
        mvc.perform(
                put("/withdraw")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getAccountJson(accountDto.getName(), accountDto.getAmount()))
        ).andExpect(status().isOk());
        verify(accountService, times(1)).withdrawMoney(accountDto);
    }

    @Test
    public void transfer() throws Exception {
        String nameFrom = "Sasha";
        String nameTo = "Ivan";
        BigDecimal amount = new BigDecimal(100);
        TransferDto transferDto = new TransferDto(nameFrom, nameTo, amount);
        mvc.perform(
                put("/transfer")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(getTransferJson(transferDto.getNameFrom(), transferDto.getNameTo(), transferDto.getAmount()))
        ).andExpect(status().isOk());
        verify(accountService, times(1)).transferMoney(transferDto);
    }

    private String getAccountJson(String name, BigDecimal amount) {
        return "{\"name\":\"" + name + "\", \"amount\":\"" + amount + "\"}";
    }

    private String getTransferJson(String nameFrom, String nameTo, BigDecimal amount) {
        return "{\"nameFrom\":\"" + nameFrom + "\", \"nameTo\":\"" + nameTo + "\", \"amount\":\"" + amount + "\"}";
    }
}