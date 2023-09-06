package com.vedha.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = { "${rabbit.mq.queue.normal.name}" })
    public void messageConsumer(String message) {

        log.error("Message Received From Queue vedha-queue : " + message);
    }
}
