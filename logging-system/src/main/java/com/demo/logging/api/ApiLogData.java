package com.demo.logging.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Data
public class ApiLogData implements Serializable {

    private String action;
    //returning log date as string due to formatting issues.
    private String logDate;
}
