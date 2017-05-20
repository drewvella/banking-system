package com.demo.logging.service;

import com.demo.logging.object.LogMessage;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Component
@Slf4j
public class LogListener {

    @Autowired
    private Gson gson;

    @Autowired
    private LoggingService loggingService;

    /**
     * This method will listen on the 'logging' queue in rabbitMQ
     * @param payload
     */
    @RabbitListener(queues = "logging")
    public void process(@Payload String payload) {
        log.info(payload);
        LogMessage logMessage = gson.fromJson(payload, LogMessage.class);
        try {
            loggingService.saveMessage(logMessage);
        } catch (Exception e) {
            log.error("Discarding message since it cannot be persisted",e);
        }
    }

}
