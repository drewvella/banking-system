package com.demo.bank.exception;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
public class ClientNotFoundException extends IllegalArgumentException {

    public ClientNotFoundException(Long clientId) {
        super("Client with id " + clientId + "not found");
    }

}
