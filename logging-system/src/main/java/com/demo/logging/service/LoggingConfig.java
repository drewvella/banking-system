package com.demo.logging.service;

import com.google.gson.Gson;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Configuration
public class LoggingConfig {


    @Bean
    Gson gson() {
        return new Gson();
    }
}
