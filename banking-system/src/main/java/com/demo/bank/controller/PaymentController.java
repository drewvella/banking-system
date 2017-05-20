package com.demo.bank.controller;

import com.demo.bank.api.ApiClient;
import com.demo.bank.api.ApiTransaction;
import com.demo.bank.api.ApiTransfer;
import com.demo.bank.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@RestController
@RequestMapping("/api/banking-system")
public class PaymentController {

    @Autowired
    BusinessService businessService;

    /**
     * Performs a transfer between 2 accounts
     * @param accountId - Source account id
     * @param transferDetails - Details of where to send the transfer
     * @return Successful transfer details
     */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/accounts/{accountId}/transfer", method = RequestMethod.POST)
    public ApiTransfer transfer(@PathVariable Long accountId, @Valid @RequestBody ApiTransfer transferDetails) {
        return businessService.transfer(transferDetails,accountId);
    }

    /**
     * Returns the transactions done on an account (credits&debits)
     * @param accountId - account id with which to retrieve transaction
     * @return List of transactions done within the system
     */
    @RequestMapping(value = "/accounts/{accountId}/transactions", method = RequestMethod.GET)
    public List<ApiTransaction> getTransactionsForAccount(@PathVariable Long accountId) {
        return businessService.getTransactionsForAccount(accountId);
    }

    /**
     * Returns the details for a specific transaction
     * @param transactionId - transaction id with whcih to search for
     * @return Transaction details
     */
    @RequestMapping(value = "/transactions/{transactionId}", method = RequestMethod.GET)
    public ApiTransaction getTransaction(@PathVariable Long transactionId) {
        return businessService.getTransaction(transactionId);
    }







}
