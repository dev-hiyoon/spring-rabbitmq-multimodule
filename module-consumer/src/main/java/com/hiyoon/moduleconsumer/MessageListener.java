package com.hiyoon.moduleconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    private static int MSG_CNT = 0;

    @RabbitListener(queues = "SAMPLE.QUE01", concurrency = "5")
    public void receiveMessage(final String message) throws InterruptedException {
        log.info("############### module-consumer. getMessgae: {}, MSG_CNT: {}", message, MSG_CNT);
        Thread.sleep(3 * 1000);
    }
}
