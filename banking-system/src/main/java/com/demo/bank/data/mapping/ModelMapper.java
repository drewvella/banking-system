package com.demo.bank.data.mapping;

import com.demo.bank.api.ApiAccount;
import com.demo.bank.api.ApiClient;
import com.demo.bank.api.ApiTransaction;
import com.demo.bank.api.ApiTransfer;
import com.demo.bank.data.model.Account;
import com.demo.bank.data.model.AccountTransaction;
import com.demo.bank.data.model.Client;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Component
public class ModelMapper extends ConfigurableMapper {

    public void configure(MapperFactory factory) {
        super.configure(factory);

        factory.registerClassMap(factory.classMap(Account.class, ApiAccount.class)
                .customize(new CustomMapper<Account, ApiAccount>() {
                    @Override
                    public void mapAtoB(Account account, ApiAccount apiAccount, MappingContext context) {
                        apiAccount.setAccountId(account.getId());
                    }
                }).byDefault().toClassMap());


        factory.registerClassMap(factory.classMap(Client.class, ApiClient.class)
                .customize(new CustomMapper<Client, ApiClient>() {
                    @Override
                    public void mapAtoB(Client client, ApiClient apiClient, MappingContext context) {
                        apiClient.setClientId(client.getId());
                        apiClient.setName(client.getClientName());
                        apiClient.setSurname(client.getClientSurname());
                    }
                }).byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(AccountTransaction.class, ApiTransaction.class)
                .customize(new CustomMapper<AccountTransaction, ApiTransaction>() {
                    @Override
                    public void mapAtoB(AccountTransaction transaction, ApiTransaction apiTransaction, MappingContext context) {
                        apiTransaction.setDestinationAccount(mapperFacade.map(apiTransaction.getDestinationAccount(), ApiAccount.class));
                        apiTransaction.setSourceAccount(mapperFacade.map(apiTransaction.getSourceAccount(),ApiAccount.class));
                    }
                }).byDefault().toClassMap());

        factory.registerClassMap(factory.classMap(AccountTransaction.class, ApiTransfer.class)
                .customize(new CustomMapper<AccountTransaction, ApiTransfer>() {
                    @Override
                    public void mapAtoB(AccountTransaction transaction, ApiTransfer apiTransfer, MappingContext context) {
                        apiTransfer.setAccountTransactionId(transaction.getId());
                        apiTransfer.setSourceAccountId(transaction.getSourceAccount().getId());
                        apiTransfer.setDestinationAccountId(transaction.getDestinationAccount().getId());
                        apiTransfer.setTransferAmount(transaction.getCreditedAmount());
                    }
                }).byDefault().toClassMap());

    }
}