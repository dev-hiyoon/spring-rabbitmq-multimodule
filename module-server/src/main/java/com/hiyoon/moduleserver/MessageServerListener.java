package com.hiyoon.moduleserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageServerListener {

    @RabbitListener(queues = MessageServerConfig.queueName)
    public RQMessage getMessage(RQMessage message) throws InterruptedException {
        log.info("getMessage. Received: {}", message.getMessage());
        Thread.sleep(3 * 1000);
        return RQMessage.builder().message("Hello world, " + message.getMessage()).build();
    }
}
