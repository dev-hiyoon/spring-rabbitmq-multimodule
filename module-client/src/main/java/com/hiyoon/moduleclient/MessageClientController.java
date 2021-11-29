package com.hiyoon.moduleclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/messages")
@RequiredArgsConstructor
public class MessageClientController {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange exchange;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/send-simple")
    public RQMessage sendSimpleMessage(@RequestBody RQMessage rQMessage) {
        RQMessage response =
                rabbitTemplate.convertSendAndReceiveAsType(
                        exchange.getName(),
                        MessageClientConfig.routingKey,
                        rQMessage,
                        new ParameterizedTypeReference<>() {
                        });

        log.info("sendMessage. response: {}", response);
        return response;
    }

    @PostMapping(value = "/send-async")
    public RQMessage sendAsyncMessage(@RequestBody RQMessage rQMessage) {
        RQMessage response =
                rabbitTemplate.convertSendAndReceiveAsType(
                        exchange.getName(),
                        MessageClientConfig.routingKey,
                        rQMessage,
                        new ParameterizedTypeReference<>() {
                        });

        log.info("sendMessage. response: {}", response);
        return response;
    }

    @PostMapping(value = "/send-reply-to")
    public RQMessage sendReplyToMessage(@RequestBody RQMessage rQMessage) {
//        String response = (String) rabbitTemplate.convertSendAndReceive(MessageClientConfig.directExchangeName,
//                MessageClientConfig.routingKey, RQMessage.getMessage());

//        rabbitTemplate.setReplyAddress("RABBIT_CLIENT_" + serverPort);


//        rabbitTemplate.setCorrelationKey("RABBIT_CLIENT_" + serverPort);
        RQMessage response =
                rabbitTemplate.convertSendAndReceiveAsType(
                        exchange.getName(),
                        MessageClientConfig.routingKey,
                        rQMessage,
                        new ParameterizedTypeReference<>() {
                        });



//        RQMessage response = rabbitTemplate.convertSendAndReceiveAsType(rQMessage,
//                message -> {
//                    message.getMessageProperties().setReceivedExchange(MessageClientConfig.directExchangeName);
//                    message.getMessageProperties().setReceivedRoutingKey(MessageClientConfig.routingKey);
//                    message.getMessageProperties().setReplyTo("RABBIT_CLIENT_" + serverPort);
//                    return message;
//                },
//                new ParameterizedTypeReference<>() {
//                });


        log.info("sendMessage. response: {}", response);
        return response;
    }
}
