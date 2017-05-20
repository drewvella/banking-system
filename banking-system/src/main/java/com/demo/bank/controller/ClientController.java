package com.demo.bank.controller;

import com.demo.bank.api.ApiAccount;
import com.demo.bank.api.ApiClient;
import com.demo.bank.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@RestController
@RequestMapping("/api/banking-system")
public class ClientController {

    @Autowired
    BusinessService businessService;

    /**
     * Creates a client in the Banking system
     * @param client - Client details required for registration
     * @return Created client details
     */
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ApiClient createClient(@Valid @RequestBody ApiClient client) {
        return businessService.createClient(client);
    }

    /**
     * Creates an account for a client in the Banking system
     * @param account - account details required for registration
     * @param clientId - client for which to create account
     * @return Created client details
     */
    @RequestMapping(value = "/clients/{clientId}/accounts", method = RequestMethod.POST)
    public ApiAccount createClientAccount(@PathVariable Long clientId, @Valid @RequestBody ApiAccount account) {
        return businessService.createAccount(account,clientId);
    }


    /**
     * Returns a client for a given client id
     * @param clientId - Client id which to retrieve
     * @return Client Details within the system
     */
    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.GET)
    public ApiClient getClient(@PathVariable Long clientId) {
        return businessService.getClient(clientId);
    }


    /**
     * Returns all the accounts for a given client id
     * @param clientId - Client id with which to search for
     * @return List of accounts for the given client
     */
    @RequestMapping(value = "/clients/{clientId}/accounts", method = RequestMethod.GET)
    public List<ApiAccount> getClientAccounts(@PathVariable Long clientId) {
        return businessService.getAccountsForClient(clientId);
    }

    /**
     * Returns the details for a specific account
     * @param accountId - Account id with which to search for
     * @return Account details within the system
     */
    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
    public ApiAccount getAccount(@PathVariable Long accountId) {
        return businessService.getAccount(accountId);
    }

}
