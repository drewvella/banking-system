package com.demo.logging.api;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
