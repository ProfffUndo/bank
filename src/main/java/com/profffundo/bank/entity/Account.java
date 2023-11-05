package com.profffundo.bank.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account", schema = "bank")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "account_id")
    private Integer accountId;

    private String number;

    private Double amount;
}
