package com.profffundo.bank.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppError {
    private String message;
    private String debugMessage;
    private HttpStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;
    public AppError(String message, String debugMessage, HttpStatus status){
        this.message=message;
        this.debugMessage=debugMessage;
        this.status=status;
    }
}
