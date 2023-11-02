package com.profffundo.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @JoinColumn(name = "account_id")
    private Integer id;

    private String number;

    private Double amount;
}
