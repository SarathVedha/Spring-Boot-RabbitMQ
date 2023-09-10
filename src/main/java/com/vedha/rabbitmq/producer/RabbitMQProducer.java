package com.vedha.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQProducer {

    // @RequiredArgsConstructor will only create object with final variable
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.mq.queue.exchange-name}")
    private String queueExchange;

    @Value("${rabbit.mq.queue.normal.routing-key-name}")
    private String queueRouting;

    public void sendMessageToQueue(String message) {

        log.error("Message Sent to Vedha Queue : " + message);

        rabbitTemplate.convertAndSend(queueExchange, queueRouting, message);
    }
}
