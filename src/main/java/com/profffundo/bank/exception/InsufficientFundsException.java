package com.profffundo.bank.exception;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(Integer id, Double amount, Double value){
        super("Недостаточно средств для операции на счете с id = " + id
                + ". Текущий баланс: "+ amount + ". Сумма операции: " + value);
    }

}
