package com.xrt.bzj.web.config;

import com.xrt.bzj.common.constant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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

    @Bean
    public Queue productSyncQueue() {
        return QueueBuilder.durable(RabbitMQConstant.PRODUCT_SYNC_QUEUE).build();
    }

    // 配置一个交换机
    @Bean
    public Exchange mesExchange() {
        return ExchangeBuilder.topicExchange(RabbitMQConstant.EXCAHNGE).build();
    }

    @Bean
    public Binding bindInit(Exchange mesExchange, Queue orderSyncQueue) {
        return BindingBuilder.bind(orderSyncQueue).to(mesExchange).with(RabbitMQConstant.ROUTING_KEY).noargs();
    }

    /**
     * 定义rabbit template用于数据的接收和发送
     * connectionFactory：连接工厂
     */
    @Bean("rabbit")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        //数据转换为json存入消息队列
        template.setMessageConverter(new Jackson2JsonMessageConverter());

        /* 若使用 confirm-callback 或 return-callback，需要配置
         * publisher-confirm-type: correlated
         * publisher-returns: true
         */
        template.setConfirmCallback(confirmCallback());
        template.setReturnCallback(returnCallback());
        /* 使用return-callback时必须设置mandatory为true，
         * 或者在配置中设置rabbitmq.template.mandatory=true
         */
        template.setMandatory(true);
        return template;
    }


    public static RabbitTemplate.ConfirmCallback confirmCallback() {
        return new RabbitTemplate.ConfirmCallback() {
            /**
             * 消息到达交换机触发回调
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                // ack判断消息发送到交换机是否成功
                System.out.println("回调id:" + correlationData.getId());
                if (ack) {
                    // 消息发送成功到达交换机
                    // ...
                    System.out.println("消息成功到达交换机");
                } else {
                    System.out.println("消息到达交换机失败");
                    System.out.println("错误信息：" + cause);
                }
            }
        };
    }

    public static RabbitTemplate.ReturnCallback returnCallback() {
        return new RabbitTemplate.ReturnCallback() {

            /**
             * 消息路由失败，回调
             * 消息(带有路由键routingKey)到达交换机，与交换机的所有绑定键进行匹配，匹配不到触发回调
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息被服务器退回。msg:{}, replyCode:{}. replyText:{}, exchange:{}, routingKey :{}",
                        new String(message.getBody()), replyCode, replyText, exchange, routingKey);
            }

        };
    }
}
