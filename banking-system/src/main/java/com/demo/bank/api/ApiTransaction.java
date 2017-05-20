package com.demo.bank.api;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Data
public class ApiTransaction implements Serializable {


    private static final long serialVersionUID = 2617149442279692904L;

    private Long id;
    private ApiAccount sourceAccount;
    private ApiAccount destinationAccount;
    private String reference;
    private BigDecimal debitedAmount;
    private BigDecimal creditedAmount;

}
