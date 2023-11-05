package com.profffundo.bank.service;

import com.profffundo.bank.dto.AmountDto;
import com.profffundo.bank.exception.AccountNotFoundException;
import com.profffundo.bank.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public AmountDto getBalanceByAccountId(Integer accountId) {
        return AmountDto.builder()
                .amount(accountRepository
                        .findById(accountId)
                        .orElseThrow(() -> new AccountNotFoundException(accountId))
                        .getBalance())
                .build();
    }

    AccountService (@Autowired AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
}
