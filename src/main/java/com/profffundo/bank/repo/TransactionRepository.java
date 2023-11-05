package com.profffundo.bank.repo;

import com.profffundo.bank.entity.Account;
import com.profffundo.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllBySenderAccount(Account senderAccount);

    List<Transaction> findAllByReceiverAccount(Account receiverAccount);

}
