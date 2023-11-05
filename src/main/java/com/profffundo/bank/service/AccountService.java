package com.profffundo.bank.service;

import com.profffundo.bank.dto.AmountDto;
import com.profffundo.bank.dto.TransactionResponseDto;
import com.profffundo.bank.entity.Account;
import com.profffundo.bank.entity.Transaction;
import com.profffundo.bank.exception.AccountNotFoundException;
import com.profffundo.bank.repo.AccountRepository;
import com.profffundo.bank.repo.TransactionRepository;
import com.profffundo.bank.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    public AmountDto getBalanceByAccountId(Integer accountId) {
        return AmountDto.builder()
                .amount(accountRepository
                        .findById(accountId)
                        .orElseThrow(() -> new AccountNotFoundException(accountId))
                        .getBalance())
                .build();
    }

    public List<TransactionResponseDto> getTransactionsByAccountId (Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        Set<Transaction> transactionList = new HashSet<>();
        transactionList.addAll(transactionRepository.findAllBySenderAccount(account));
        transactionList.addAll(transactionRepository.findAllByReceiverAccount(account));

        return transactionList
                .stream()
                .map(MapperUtils::mapTransactionToDto)
                .toList();
    }

    AccountService (@Autowired AccountRepository accountRepository,
                    @Autowired TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
}
