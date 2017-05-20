package com.demo.bank.service.impl;

import com.demo.bank.data.model.Account;
import com.demo.bank.data.model.AccountTransaction;
import com.demo.bank.data.model.Client;
import com.demo.bank.data.model.QAccountTransaction;
import com.demo.bank.data.repository.AccountRepository;
import com.demo.bank.data.repository.AccountTransactionRepository;
import com.demo.bank.data.repository.ClientRepository;
import com.demo.bank.exception.AccountNotFoundException;
import com.demo.bank.exception.ClientNotFoundException;
import com.demo.bank.service.DataService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Component
public class DataServiceImpl implements DataService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountTransactionRepository accountTransactionRepository;

    @Override
    public Account createAccount(String description, Long clientId) throws ClientNotFoundException {
        Client client = clientRepository.findOne(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }

        Account account = new Account();
        account.setDescription(description);
        account.setClient(client);
        return accountRepository.save(account);
    }

    @Override
    public Client createClient(String name, String surname) {
        Client client = new Client();
        client.setClientName(name);
        client.setClientSurname(surname);
        return clientRepository.save(client);
    }

    @Override
    public AccountTransaction createAccountTransaction(Long sourceAccountId, Long destinationAccountId, String reference, BigDecimal debitedAmount, BigDecimal creditedAmount) {
        Account sourceAccount = accountRepository.findOne(sourceAccountId);
        Account destinationAccount = accountRepository.findOne(destinationAccountId);
        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setDestinationAccount(destinationAccount);
        accountTransaction.setSourceAccount(sourceAccount);
        accountTransaction.setDebitedAmount(debitedAmount);
        accountTransaction.setCreditedAmount(creditedAmount);
        accountTransaction.setReference(reference);
        return accountTransactionRepository.save(accountTransaction);

    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long accountId) {
        Account account = accountRepository.findOne(accountId);
        if (account == null) {
            throw new AccountNotFoundException(accountId);
        }
        return account;
    }

    @Override
    public Client getClient(Long clientId) {
        Client client = clientRepository.findOne(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return client;
    }

    @Override
    public List<AccountTransaction> getAccountTransactions(Long accountId) {
        //Using Querydsl predicate here since account can be either a source or destination
        Iterable<AccountTransaction> results = accountTransactionRepository.findAll(QAccountTransaction.accountTransaction.destinationAccount.id.eq(accountId).or(QAccountTransaction.accountTransaction.sourceAccount.id.eq(accountId)));
        return Lists.newArrayList(results);
    }

    @Override
    @Transactional
    public Account getAndLockAcccount(Long accountId) {
        Account account = accountRepository.findByIdAndLock(accountId);
        if (account == null) {
            throw new AccountNotFoundException(accountId);
        }
        return account;
    }

    @Override
    @Transactional
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public AccountTransaction getAccountTransaction(Long accountTransactionId) {
        return accountTransactionRepository.findOne(accountTransactionId);
    }
}
