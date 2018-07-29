package com.example.accounttransactions.controller;

import com.example.accounttransactions.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        mvc.perform(get("/"));
        verify(accountService, times(1)).getAccounts();
    }

    @Test
    public void put() throws Exception {
        String name = "Sasha";
        BigDecimal amount = new BigDecimal(100);
        mvc.perform(get(String.format("/put?name=%s&amount=%s", name, amount))).andExpect(status().isOk());
        verify(accountService, times(1)).changeBalance(name, amount);
    }

    @Test
    public void withdraw() throws Exception {
        String name = "Sasha";
        BigDecimal amount = new BigDecimal(100);
        mvc.perform(get(String.format("/withdraw?name=%s&amount=%s", name, amount))).andExpect(status().isOk());
        verify(accountService, times(1)).changeBalance(name, amount.negate());
    }

    @Test
    public void transfer() throws Exception {
        String nameFrom = "Sasha";
        String nameTo = "Ivan";
        BigDecimal amount = new BigDecimal(100);
        mvc.perform(get(String.format("/transfer?nameFrom=%s&nameTo=%s&amount=%s", nameFrom, nameTo, amount))).andExpect(status().isOk());
        verify(accountService, times(1)).transferMoney(nameFrom, nameTo, amount);
    }
}