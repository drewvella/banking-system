package com.demo.bank.service;

import com.demo.bank.data.model.Account;
import com.demo.bank.data.model.AccountTransaction;
import com.demo.bank.data.model.Client;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
public interface DataService {

    Account createAccount(String description, Long clientId);

    Client createClient(String name, String surname);

    AccountTransaction createAccountTransaction(Long sourceAccountId, Long destinationAccountId, String reference, BigDecimal debitedAmount, BigDecimal creditedAmount);

    Account getAccount(Long accountId);

    Client getClient(Long clientId);

    List<AccountTransaction> getAccountTransactions(Long accountId);

    Account getAndLockAcccount(Long accountId);

    Account updateAccount(Account account);

    AccountTransaction getAccountTransaction(Long accountTransactionId);


}
