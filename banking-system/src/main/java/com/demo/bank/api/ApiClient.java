package com.demo.bank.api;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Data
public class ApiClient implements Serializable {


    private static final long serialVersionUID = 2284641076493493217L;

    private Long clientId;
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;
}
