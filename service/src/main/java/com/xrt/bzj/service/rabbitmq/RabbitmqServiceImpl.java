package com.xrt.bzj.service.rabbitmq;

import com.xrt.bzj.common.constant.RabbitMQConstant;
import com.xrt.bzj.dao.entity.Order;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.mq.impl
 * @date 2021/7/1 22:31
 */
@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Order order) {
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCAHNGE, RabbitMQConstant.ROUTING_KEY, order);
    }


    @Override
    public void publishWishPros(Order order) {

        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCAHNGE, RabbitMQConstant.ROUTING_KEY, order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setCorrelationId("445322888");
                return message;
            }
        });
    }

    @Override
    public void publishWishConfirms(Order order) {
        rabbitTemplate. (new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    System.out.println("消息到达交换机");
                } else {
                    System.out.println("消息没有到达交换机, 需要补偿操作");
                }
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("消息路由队列失败，做补救操作");
            }
        });

        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCAHNGE, RabbitMQConstant.ROUTING_KEY, order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setCorrelationId("445322888");
                return message;
            }
        });
    }
}
