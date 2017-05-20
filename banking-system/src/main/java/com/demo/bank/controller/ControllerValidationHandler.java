package com.demo.bank.controller;

import com.demo.bank.exception.AccountNotFoundException;
import com.demo.bank.exception.ClientNotFoundException;
import com.demo.bank.exception.NotEnoughBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@ControllerAdvice
public class ControllerValidationHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();
        return error.getDefaultMessage();
    }

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String processClientNotFoundError(ClientNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String processAccountNotFoundError(AccountNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String processNotEnoughBalanceError(NotEnoughBalanceException ex) {
        return ex.getMessage();
    }




}

