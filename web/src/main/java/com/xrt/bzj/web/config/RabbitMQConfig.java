package com.xrt.bzj.web.config;

import com.xrt.bzj.common.constant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class RabbitMQConfig {

    // 配置一个交换机
    @Bean
    public Exchange mesExchange() {
        return ExchangeBuilder.topicExchange(RabbitMQConstant.EXCAHNGE).build();
    }

    // 配置队列
    @Bean
    public Queue orderSyncQueue() {
        return QueueBuilder.durable(RabbitMQConstant.ORDER_SYNC_QUEUE).build();
    }

    @Bean
    public Queue productSyncQueue() {
        return QueueBuilder.durable(RabbitMQConstant.PRODUCT_SYNC_QUEUE).build();
    }

    // 绑定
    @Bean
    public Binding orderSyncBind(Exchange mesExchange, Queue orderSyncQueue) {
        return BindingBuilder.bind(orderSyncQueue).to(mesExchange).with(RabbitMQConstant.ORDER_SYNC_ROUTING_KEY).noargs();
    }

    @Bean
    public Binding productSyncBind(Exchange mesExchange, Queue productSyncQueue) {
        return BindingBuilder.bind(productSyncQueue).to(mesExchange).with(RabbitMQConstant.PRODUCT_SYNC_ROUTING_KEY).noargs();
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        // 确认投递到交换机回调
        template.setConfirmCallback(new RabbitMQCallback());
        // 开启mandatory模式（开启失败回调）
        template.setMandatory(true);
        //未投递到queue回调
        template.setReturnCallback(new RabbitMQCallback());

        return template;
    }


}
