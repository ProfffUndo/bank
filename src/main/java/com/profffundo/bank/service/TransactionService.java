package com.profffundo.bank.service;

import com.profffundo.bank.dto.TransactionRequestDto;
import com.profffundo.bank.dto.TransactionResponseDto;
import com.profffundo.bank.entity.Account;
import com.profffundo.bank.entity.Transaction;
import com.profffundo.bank.exception.AccountNotFoundException;
import com.profffundo.bank.exception.InsufficientFundsException;
import com.profffundo.bank.exception.NegativeSumException;
import com.profffundo.bank.exception.TransactionNotFoundException;
import com.profffundo.bank.repo.AccountRepository;
import com.profffundo.bank.repo.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.profffundo.bank.utils.MapperUtils.mapTransactionToDto;

@Service
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public TransactionResponseDto transfer (TransactionRequestDto requestDto){
        Integer senderId = requestDto.getSenderId();
        Integer receiverId = requestDto.getReceiverId();
        Double sum = requestDto.getSum();
        Account sender = accountRepository.findById(senderId)
                .orElseThrow(() -> new AccountNotFoundException(senderId));
        Account receiver = accountRepository.findById(receiverId)
                .orElseThrow(() -> new AccountNotFoundException(receiverId));

        if (sum < 0){
            throw new NegativeSumException(sum);
        }
        if (sender.getBalance() - sum < 0){
            throw new InsufficientFundsException(senderId, sender.getBalance(), sum);
        }
        else {
            sender.setBalance(sender.getBalance() - sum);
            receiver.setBalance(receiver.getBalance() + sum);
        }

        Transaction transaction = Transaction.builder()
                .senderAccount(sender)
                .receiverAccount(receiver)
                .sum(sum)
                .tranTime(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);

        return mapTransactionToDto(transaction);
    }

    @Transactional
    public TransactionResponseDto changeBalance (TransactionRequestDto requestDto){
        Integer accountId = requestDto.getReceiverId();
        Double sum = requestDto.getSum();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        if (sum < 0 && (account.getBalance() + sum < 0)){
            throw new InsufficientFundsException(accountId, account.getBalance(), sum);
        }
        else{
            Transaction transaction = Transaction.builder()
                    .receiverAccount(account)
                    .sum(sum)
                    .tranTime(LocalDateTime.now())
                    .build();
            transactionRepository.save(transaction);

            return mapTransactionToDto(transaction);
        }
    }

    public TransactionResponseDto getTransaction (Integer transactionId){
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException(transactionId));
        return mapTransactionToDto(transaction);
    }

    TransactionService (@Autowired AccountRepository accountRepository,
                        @Autowired TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
}
