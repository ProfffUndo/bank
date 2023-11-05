package com.profffundo.bank.exception;

public class NegativeSumException extends RuntimeException{
    public NegativeSumException(Double value){
        super("Негативная сумма операции +" + value);
    }

}
