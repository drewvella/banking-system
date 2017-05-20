package com.demo.bank.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Data
public class ApiTransfer implements Serializable {

    private static final long serialVersionUID = -2566957848749242890L;

    @NotNull
    private Long destinationAccountId;

    @NotNull()
    @Min(0)
    private BigDecimal transferAmount;

    @NotNull
    @NotEmpty
    private String reference;

    private Long sourceAccountId;
    private BigDecimal resultingBalance;
    private Long accountTransactionId;

}
