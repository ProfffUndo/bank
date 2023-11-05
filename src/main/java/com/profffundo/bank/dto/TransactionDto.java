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
public class TransactionDto {

    private String senderAccountNumber;

    private String receiverAccountNumber;

    private Double value;

    private LocalDateTime time;
}
