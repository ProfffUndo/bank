package com.profffundo.bank.controller;

import com.profffundo.bank.dto.AmountDto;
import com.profffundo.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/account/{id}/balance")
    public AmountDto getAccountBalance(@PathVariable Integer id){
        return accountService.getBalanceByAccountId(id);
    }

    AccountController(@Autowired AccountService accountService){
        this.accountService=accountService;
    }
}
