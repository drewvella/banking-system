package com.demo.bank.service.impl;

import com.demo.bank.api.ApiTransfer;
import com.demo.bank.data.mapping.ModelMapper;
import com.demo.bank.data.model.Account;
import com.demo.bank.exception.NotEnoughBalanceException;
import com.demo.bank.service.DataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BusinessServiceImplTest {


    BusinessServiceImpl businessService = new BusinessServiceImpl();

    ApiTransfer transfer;

    @Mock
    DataService dataService;

    @Mock
    ModelMapper modelMapper;

    @Before
    public final void setup() {
        businessService.setDataService(dataService);
        businessService.setModelMapper(modelMapper);
        transfer = new ApiTransfer();
        transfer.setTransferAmount(BigDecimal.TEN);
        transfer.setDestinationAccountId(2L);


    }

    @Test(expected = NotEnoughBalanceException.class)
    public void transferShouldThrowNotEnoughBalanceException() {
        Account account = new Account();
        account.setBalance(BigDecimal.ONE);
        when(dataService.getAndLockAcccount(anyLong())).thenReturn(account);
        businessService.transfer(transfer,1L);
    }


    @Test
    public void transferShouldUpdateBalancesProperly() {
        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        Account sourceAccount = new Account();
        sourceAccount.setBalance(BigDecimal.TEN);
        sourceAccount.setId(1L);
        when(dataService.getAndLockAcccount(1L)).thenReturn(sourceAccount);
        Account destinationAccount = new Account();
        destinationAccount.setBalance(BigDecimal.ZERO);
        destinationAccount.setId(2L);
        when(dataService.getAndLockAcccount(2L)).thenReturn(destinationAccount);
        when(modelMapper.map(any(),any())).thenReturn(new ApiTransfer());
        businessService.transfer(transfer,1L);
        verify(dataService,times(2)).updateAccount(captor.capture());

        List<Account> accountList = captor.getAllValues();
        assertTrue(accountList.get(0).getBalance().equals(BigDecimal.ZERO));
        assertTrue((accountList.get(1).getBalance().equals(BigDecimal.TEN)));
    }



}
