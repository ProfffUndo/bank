package com.profffundo.bank.controller;

import com.profffundo.bank.validation.TransactionInfo;
import com.profffundo.bank.validation.TransferInfo;
import com.profffundo.bank.dto.TransactionRequestDto;
import com.profffundo.bank.dto.TransactionResponseDto;
import com.profffundo.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    private final TransactionService transactionService;
    @PostMapping("/transfer")
    public TransactionResponseDto transfer (@RequestBody @Validated(TransferInfo.class) TransactionRequestDto requestDto){
        return transactionService.transfer(requestDto);
    }

    @PostMapping("/transaction")
    public TransactionResponseDto changeBalance (@RequestBody @Validated(TransactionInfo.class) TransactionRequestDto requestDto){
        return transactionService.changeBalance(requestDto);
    }

    @GetMapping("/transaction/{id}")
    public TransactionResponseDto getTransaction (@PathVariable Integer id){
        return transactionService.getTransaction(id);
    }

    TransactionController(@Autowired TransactionService transactionService){
        this.transactionService=transactionService;
    }
}
