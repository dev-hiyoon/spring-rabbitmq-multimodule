package com.hiyoon.moduleclient;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessageClientConfig {
    public static final String directExchangeName = "SAMPLE.EX02";
    public static final String routingKey = "RKEY.EX02.RPC";

    @Bean
    DirectExchange simpleExchange() {
        return new DirectExchange(directExchangeName);
    }
//
//    @Bean
//    public MessageConverter jackson2MessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
}
