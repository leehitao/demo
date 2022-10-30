package com.xrt.bzj.web.config;

import com.xrt.bzj.common.constant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class RabbitMQConfig {


    // 配置队列
    @Bean
    public Queue orderSyncQueue() {
        return QueueBuilder.durable(RabbitMQConstant.ORDER_SYNC_QUEUE).build();
    }

//    @Bean
//    public Queue productSyncQueue() {
//        return QueueBuilder.durable(RabbitMQConstant.PRODUCT_SYNC_QUEUE).build();
//    }

    // 配置一个交换机
    @Bean
    public Exchange mesExchange() {
        return ExchangeBuilder.topicExchange(RabbitMQConstant.EXCAHNGE).build();
    }

    @Bean
    public Binding bindInit(Exchange mesExchange, Queue orderSyncQueue) {
        return BindingBuilder.bind(orderSyncQueue).to(mesExchange).with(RabbitMQConstant.ROUTING_KEY).noargs();
    }


}
