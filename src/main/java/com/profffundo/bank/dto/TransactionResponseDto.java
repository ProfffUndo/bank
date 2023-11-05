package com.profffundo.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDto {

    private String senderAccountNumber;

    private String receiverAccountNumber;

    private Double sum;

    private LocalDateTime time;
}
