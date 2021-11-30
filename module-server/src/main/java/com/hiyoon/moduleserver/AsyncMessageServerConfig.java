package com.hiyoon.moduleserver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncMessageServerConfig {

    public static final String async_request_queue = "SAMPLE.QUE.ASYNC.REQUEST";

    @Bean
    public MessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Queue asyncRequestQueue() {
        return QueueBuilder.durable(async_request_queue).build();
    }
}
