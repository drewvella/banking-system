package com.demo.bank.exception;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
public class NotEnoughBalanceException extends IllegalArgumentException {

    public NotEnoughBalanceException() {
        super("Not enough balance!");
    }
}
