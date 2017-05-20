package com.demo.bank.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Data
public class ApiAccount implements Serializable {


    private static final long serialVersionUID = 6327191284458591017L;

    private Long accountId;

    @NotNull
    @NotEmpty
    private String description;

    private BigDecimal balance;


}
