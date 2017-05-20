package com.demo.bank.service;

import com.demo.bank.api.ApiAccount;
import com.demo.bank.api.ApiClient;
import com.demo.bank.api.ApiTransaction;
import com.demo.bank.api.ApiTransfer;
import com.demo.bank.exception.AccountNotFoundException;
import com.demo.bank.exception.ClientNotFoundException;
import com.demo.bank.exception.NotEnoughBalanceException;

import java.util.List;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
public interface BusinessService {


    ApiAccount getAccount(Long accountId) throws AccountNotFoundException;

    ApiClient getClient(Long cientId) throws ClientNotFoundException;

    List<ApiAccount> getAccountsForClient(Long clientId) throws ClientNotFoundException;

    List<ApiTransaction> getTransactionsForAccount(Long accountId) throws AccountNotFoundException;

    ApiTransaction getTransaction(Long accountTransactionId);

    ApiClient createClient(ApiClient clientDetails);

    ApiAccount createAccount(ApiAccount accountDetails, Long clientId) throws ClientNotFoundException;

    ApiTransfer transfer(ApiTransfer transferDetails, Long accountId) throws AccountNotFoundException, NotEnoughBalanceException;


}
