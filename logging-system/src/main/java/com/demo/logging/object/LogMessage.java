package com.demo.logging.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Data
@AllArgsConstructor
public class LogMessage implements Serializable {

    private static final long serialVersionUID = -3508591969674209886L;

    private String dateTime;
    private String userId;
    private String action;



}
