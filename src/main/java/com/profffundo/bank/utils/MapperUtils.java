package com.profffundo.bank.utils;

import com.profffundo.bank.dto.TransactionResponseDto;
import com.profffundo.bank.entity.Transaction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MapperUtils {
    public static TransactionResponseDto mapTransactionToDto (Transaction transaction){
        return TransactionResponseDto.builder()
                .receiverAccountNumber(transaction.getReceiverAccount().getNumber())
                .sum(transaction.getSum())
                .time(transaction.getTranTime())
                .build();
    }
}
