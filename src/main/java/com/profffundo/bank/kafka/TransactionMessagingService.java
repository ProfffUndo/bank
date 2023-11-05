package com.profffundo.bank.kafka;

import com.profffundo.bank.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionMessagingService {
    @Value("${topic.send-transaction}")
    private String sendTransactionTopic;

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public void sendTransaction (Transaction transaction){
        kafkaTemplate.send(sendTransactionTopic, transaction);
    }

}
