package com.demo.bank.logging;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Component
public class LogPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queuename}")
    private String queueName;

    @Autowired
    private Gson gson;

    public void send(LogMessage logMessage) {
        String payload = convertToJsonString(logMessage);
        rabbitTemplate.convertAndSend(queueName,payload);
    }


    public String convertToJsonString(Object o) {
        return gson.toJson(o);
    }
}
