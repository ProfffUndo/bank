package com.profffundo.bank.controller;

import com.profffundo.bank.dto.AmountDto;
import com.profffundo.bank.dto.TransactionDto;
import com.profffundo.bank.dto.TransactionRequestDto;
import com.profffundo.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final AccountService service;

    @GetMapping("/account/{id}/amount")
    public AmountDto getAccountAmount(@PathVariable Integer id){
        return service.getAmountByAccountId(id);
    }

    @PostMapping("/transfer")
    public TransactionDto transfer (@RequestBody TransactionRequestDto requestDto){
        return service.transfer(requestDto);
    }

    AccountController(@Autowired AccountService service){
        this.service=service;
    }
}
