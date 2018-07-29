package com.example.accounttransactions.controller;

import com.example.accounttransactions.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    public void put() throws Exception {
        mvc.perform(get("/put?name=Sasha&amount=100")).andExpect(status().isOk());
    }

    @Test
    public void withdraw() throws Exception {
        mvc.perform(get("/withdraw?name=Sasha&amount=100")).andExpect(status().isOk());
    }

    @Test
    public void transfer() throws Exception {
        mvc.perform(get("/transfer?nameFrom=Sasha&nameTo=Ivan&amount=100.1")).andExpect(status().isOk());
    }
}