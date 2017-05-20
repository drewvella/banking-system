package com.demo.bank.exception;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
public class AccountNotFoundException extends IllegalArgumentException {

    public AccountNotFoundException(Long accountId) {
        super("Account with id " + accountId + "not found");
    }

}
