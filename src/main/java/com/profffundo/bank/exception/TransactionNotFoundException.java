package com.profffundo.bank.exception;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(Integer id){
        super("Траназакция с id = " + id + " не найдена");
    }

}
