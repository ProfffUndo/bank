package com.profffundo.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction", schema = "bank")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "tran_id")
    private Integer tranId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Account receiverAccount;

    private Double sum;

    @JoinColumn(name = "tran_time")
    private LocalDateTime tranTime;

}
