package com.profffundo.bank.exception.handler;

import com.profffundo.bank.exception.AccountNotFoundException;
import com.profffundo.bank.exception.InsufficientFundsException;
import com.profffundo.bank.exception.NegativeSumException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<AppError> catchAccountNotFoundException (AccountNotFoundException e){
        AppError error = AppError.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InsufficientFundsException.class, NegativeSumException.class})
    public ResponseEntity<AppError> catchImpossibleOperationExceptions (InsufficientFundsException e){
        AppError error = AppError.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
