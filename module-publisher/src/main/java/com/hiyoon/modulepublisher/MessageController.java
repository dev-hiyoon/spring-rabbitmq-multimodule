package com.hiyoon.modulepublisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages")
@RequiredArgsConstructor
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/send")
    public void sendMessage(@RequestBody RQMessage RQMessage) {
        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE_NAME, MessageConfig.ROUTING_KEY, RQMessage);
    }

}
