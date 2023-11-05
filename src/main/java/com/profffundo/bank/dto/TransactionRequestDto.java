package com.profffundo.bank.dto;

import com.profffundo.bank.validation.TransactionInfo;
import com.profffundo.bank.validation.TransferInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequestDto {

    @NotNull(groups = TransferInfo.class)
    private Integer senderId;

    @NotNull(groups = {TransferInfo.class, TransactionInfo.class})
    private Integer receiverId;

    @NotNull(groups = {TransferInfo.class, TransactionInfo.class})
    private Double sum;
}
