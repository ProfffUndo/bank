package com.profffundo.bank.service;

import com.profffundo.bank.dto.TransactionDto;
import com.profffundo.bank.dto.AmountDto;
import com.profffundo.bank.dto.TransactionRequestDto;
import com.profffundo.bank.entity.Account;
import com.profffundo.bank.entity.Transaction;
import com.profffundo.bank.exception.AccountNotFoundException;
import com.profffundo.bank.exception.InsufficientFundsException;
import com.profffundo.bank.exception.NegativeSumException;
import com.profffundo.bank.repo.AccountRepository;
import com.profffundo.bank.repo.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    public AmountDto getAmountByAccountId(Integer accountId){
        return AmountDto.builder()
                .amount(accountRepository
                .findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId))
                .getAmount())
                .build();
    }

    @Transactional
    public TransactionDto transfer (TransactionRequestDto requestDto){
        Integer senderId = requestDto.getSenderId();
        Integer receiverId = requestDto.getReceiverId();
        Double value = requestDto.getValue();
        Account sender = accountRepository.findById(senderId).orElseThrow(() -> new AccountNotFoundException(senderId));
        Account receiver = accountRepository.findById(receiverId).orElseThrow(() -> new AccountNotFoundException(receiverId));

        if (value < 0){
            throw new NegativeSumException(value);
        }
        if (sender.getAmount() - value < 0){
            throw new InsufficientFundsException(senderId, sender.getAmount(), value);
        }
        else {
            sender.setAmount(sender.getAmount() - value);
            receiver.setAmount(receiver.getAmount() + value);
        }

        Transaction transaction = Transaction.builder()
                .senderAccount(sender)
                .receiverAccount(receiver)
                .value(value)
                .tranTime(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);

        return TransactionDto.builder()
                .senderAccountNumber(sender.getNumber())
                .receiverAccountNumber(receiver.getNumber())
                .value(value)
                .time(transaction.getTranTime())
                .build();
    }

    AccountService (@Autowired AccountRepository accountRepository,
                    @Autowired TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
}
