package com.demo.bank.service.impl;

import com.demo.bank.api.ApiAccount;
import com.demo.bank.api.ApiClient;
import com.demo.bank.api.ApiTransaction;
import com.demo.bank.api.ApiTransfer;
import com.demo.bank.data.mapping.ModelMapper;
import com.demo.bank.data.model.Account;
import com.demo.bank.data.model.AccountTransaction;
import com.demo.bank.data.model.Client;
import com.demo.bank.exception.AccountNotFoundException;
import com.demo.bank.exception.ClientNotFoundException;
import com.demo.bank.exception.NotEnoughBalanceException;
import com.demo.bank.service.BusinessService;
import com.demo.bank.service.DataService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    @Setter
    private DataService dataService;


    @Autowired
    @Setter
    private ModelMapper modelMapper;


    @Override
    public ApiAccount getAccount(Long accountId) throws AccountNotFoundException {
        Account account = dataService.getAccount(accountId);
        //map to external object
        return modelMapper.map(account, ApiAccount.class);
    }

    @Override
    public ApiClient getClient(Long clientId) throws ClientNotFoundException {
        Client client = dataService.getClient(clientId);
        //map to external object
        return modelMapper.map(client, ApiClient.class);
    }

    @Override
    public List<ApiAccount> getAccountsForClient(Long clientId) throws ClientNotFoundException {

        Client client = dataService.getClient(clientId);
        //map to external object
        return modelMapper.mapAsList(client.getAccounts(), ApiAccount.class);
    }

    @Override
    public List<ApiTransaction> getTransactionsForAccount(Long accountId) throws AccountNotFoundException {
        //Call get account to verify account exists
        Account account = dataService.getAccount(accountId);
        List<AccountTransaction> accountTransactions = dataService.getAccountTransactions(accountId);
        //map to external object
        return modelMapper.mapAsList(accountTransactions, ApiTransaction.class);
    }

    @Override
    public ApiTransaction getTransaction(Long accountTransactionId) {
        AccountTransaction accountTransaction = dataService.getAccountTransaction(accountTransactionId);
        //map to external object
        return modelMapper.map(accountTransaction, ApiTransaction.class);
    }

    @Override
    public ApiClient createClient(ApiClient clientDetails) {
        Client client = dataService.createClient(clientDetails.getName(), clientDetails.getSurname());
        //map to external object
        return modelMapper.map(client, ApiClient.class);
    }

    @Override
    public ApiAccount createAccount(ApiAccount accountDetails, Long clientId) throws ClientNotFoundException {
        Account account = dataService.createAccount(accountDetails.getDescription(), clientId);
        //map to external object
        return modelMapper.map(account, ApiAccount.class);
    }

    @Override
    @Transactional
    public ApiTransfer transfer(ApiTransfer transferDetails, Long accountId) throws AccountNotFoundException {
        //Lock both source and destination accounts for updating
        Account sourceAccount = dataService.getAndLockAcccount(accountId);
        Account destinationAccount = dataService.getAndLockAcccount(transferDetails.getDestinationAccountId());

        BigDecimal transferAmount = transferDetails.getTransferAmount();

        //Check if source account has enough balance for the transfer amount
        if (sourceAccount.getBalance().compareTo(transferAmount) < 0) {
            throw new NotEnoughBalanceException();
        }

        //Subtract balance from source
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferAmount));
        //Add balance to destination
        destinationAccount.setBalance(destinationAccount.getBalance().add(transferAmount));

        //Persist updated balances to db
        dataService.updateAccount(sourceAccount);
        dataService.updateAccount(destinationAccount);

        //Create account transaction
        AccountTransaction accountTransaction = dataService.createAccountTransaction(sourceAccount.getId(), destinationAccount.getId(), transferDetails.getReference(), transferAmount.negate(), transferAmount);

        //map to external object
        ApiTransfer result = modelMapper.map(accountTransaction, ApiTransfer.class);
        //return remaining balance from source account
        result.setResultingBalance(sourceAccount.getBalance());
        return result;
    }
}
