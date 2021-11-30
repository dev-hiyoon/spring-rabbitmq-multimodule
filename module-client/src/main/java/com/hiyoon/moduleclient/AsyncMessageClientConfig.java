package com.hiyoon.moduleclient;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AsyncMessageClientConfig {

    public static final String async_directExchangeName = "SAMPLE.EX.ASYNC";
    public static final String async_request_queue = "SAMPLE.QUE.ASYNC.REQUEST";
    public static final String async_reply_queue = "SAMPLE.QUE.ASYNC.REPLY";
    public static final String async_routingKey = "SAMPLE.RKEY.ASYNC";


    @Bean
    DirectExchange asyncExchange() {
        return new DirectExchange(async_directExchangeName);
    }

    @Bean
    Queue requestQueue() {
        return QueueBuilder.durable(async_request_queue).build();
    }

    @Bean
    Queue replyQueue() {
        return QueueBuilder.durable(async_reply_queue).build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(requestQueue()).to(asyncExchange()).with(async_routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    AsyncRabbitTemplate template(RabbitTemplate rabbitTemplate, ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(async_reply_queue);
        return new AsyncRabbitTemplate(rabbitTemplate, container);
    }
}
