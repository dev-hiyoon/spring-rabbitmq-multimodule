package com.hiyoon.moduleclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping(value = "/messages")
@RequiredArgsConstructor
public class MessageClientController {

    private final RabbitTemplate rabbitTemplate;
    private final AsyncRabbitTemplate asyncRabbitTemplate;
    private final DirectExchange simpleExchange;
    private final DirectExchange asyncExchange;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/send-simple")
    public RQMessage sendSimpleMessage(@RequestBody RQMessage rQMessage) {

        log.info("sendMessage. request: {}", rQMessage);
        RQMessage response = rabbitTemplate.convertSendAndReceiveAsType(
                simpleExchange.getName(),
                MessageClientConfig.routingKey,
                rQMessage,
                new ParameterizedTypeReference<>() {
                });

        log.info("sendMessage. response: {}", response);
        return response;
    }

    @PostMapping(value = "/send-async")
    public RQMessage sendAsyncMessage(@RequestBody RQMessage rQMessage) {
        ListenableFuture<RQMessage> future = asyncRabbitTemplate.convertSendAndReceiveAsType(
                asyncExchange.getName(),
                AsyncMessageClientConfig.async_routingKey,
                rQMessage,
                new ParameterizedTypeReference<>() {
                });

        RQMessage reply = null;
        try {
            reply = future.get();
            log.info("sendMessage. response: {}", reply);
        } catch (ExecutionException | InterruptedException e) {
            log.error("sendMessage. response: {}", reply);
        }

        return reply;
    }

    @PostMapping(value = "/send-reply-to")
    public RQMessage sendReplyToMessage(@RequestBody RQMessage rQMessage) {

        return null;
    }
}
