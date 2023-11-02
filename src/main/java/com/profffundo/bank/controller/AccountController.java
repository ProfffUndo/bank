package com.profffundo.bank.controller;

import com.profffundo.bank.dto.ValueDto;
import com.profffundo.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountService service;

    @GetMapping("/account/{id}/amount")
    public ValueDto getAccountAmount(@PathVariable Integer id){
        return service.getAmountByAccountId(id);
    }

    AccountController(@Autowired AccountService service){
        this.service=service;
    }
}
