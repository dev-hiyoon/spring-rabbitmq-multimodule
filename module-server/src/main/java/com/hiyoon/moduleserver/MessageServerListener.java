package com.hiyoon.moduleserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageServerListener {

    @RabbitListener(queues = MessageServerConfig.queueName)
    public RQMessage getMessage(RQMessage message) throws InterruptedException {
        log.info("getMessage. Received: {}", message.getMessage());
        log.info("getMessage. Send: {}", message.getMessage());
        return RQMessage.builder().message("Hello world simple, " + message.getMessage()).build();
    }

    @RabbitListener(queues = AsyncMessageServerConfig.async_request_queue)
    public RQMessage process(@Payload RQMessage request) throws InterruptedException {
        Thread.sleep(10 * 1000);
        log.info("Received '{}'", request.getMessage());
        return RQMessage.builder().message("Hello world async, " + request.getMessage()).build();
    }
}
