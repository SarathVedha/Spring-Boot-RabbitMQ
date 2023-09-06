package com.vedha.rabbitmq.consumer;

import com.vedha.rabbitmq.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = { "${rabbit.mq.queue.json.name}" })
    public void consumeJsonMessage(UserDTO userDTO) {

        log.error("Json Message Received From Queue vedha-json-queue : " + userDTO);
    }
}
