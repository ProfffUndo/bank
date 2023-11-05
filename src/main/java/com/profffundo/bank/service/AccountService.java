package com.profffundo.bank.service;

import com.profffundo.bank.dto.ValueDto;
import com.profffundo.bank.exception.AccountNotFoundException;
import com.profffundo.bank.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository repository;
    public ValueDto getAmountByAccountId(Integer accountId){
        return ValueDto.builder()
                .value(repository
                .findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId))
                .getAmount())
                .build();
    }

    AccountService (@Autowired AccountRepository repository){
        this.repository = repository;
    }
}
