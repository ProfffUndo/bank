package com.profffundo.bank.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(Integer id){
        super("Аккаунт с id = " + id + " не найден");
    }
}
