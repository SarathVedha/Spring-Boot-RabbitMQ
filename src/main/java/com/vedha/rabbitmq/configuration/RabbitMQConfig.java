package com.vedha.rabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbit.mq.queue.exchange-name}")
    private String queueExchangeName;

    @Value("${rabbit.mq.queue.normal.name}")
    private String queueName;

    @Value("${rabbit.mq.queue.normal.routing-key-name}")
    private String queueRoutingKey;

    @Value("${rabbit.mq.queue.json.name}")
    private String jsonQueueName;

    @Value("${rabbit.mq.queue.json.routing-key-name}")
    private String jsonQueueRoutingKey;

    /*
    * Note, This Three Bean Will be Auto Configured by spring.
    * ConnectionFactory
    * RabbitTemplate
    * RabbitAdmin
    * */

    // Bean Of Spring RabbitMq Queue
    @Bean
    Queue queueVedha() {

        return new Queue(queueName);
    }

    // Bean For Spring RabbitMq Queue Exchange
    @Bean
    TopicExchange exchangeVedha() {

        return new TopicExchange(queueExchangeName);
    }

    // Binding Queue and Exchange with routing key
    @Bean
    Binding routingKeyBindingVedha() {

        return BindingBuilder.bind(queueVedha()).to(exchangeVedha()).with(queueRoutingKey);
    }

    // Json
    @Bean
    Queue jsonQueueVedha() {

        return new Queue(jsonQueueName);
    }

    @Bean
    Binding routingKeyBindingJsonVedha() {

        return BindingBuilder.bind(jsonQueueVedha()).to(exchangeVedha()).with(jsonQueueRoutingKey);
    }

    // For Supporting Json(its converts java dto object to json and vise versa)
    @Bean
    MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    // Setting rabbitTemplate to support message converter for json
    @Bean
    AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());

        return rabbitTemplate;
    }
}
