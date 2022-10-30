package com.xrt.bzj.service.rabbitmq;

import com.xrt.bzj.common.constant.RabbitMQConstant;
import com.xrt.bzj.dao.entity.Order;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCAHNGE, RabbitMQConstant.ORDER_SYNC_ROUTING_KEY, order);
    }

    @Override
    public void publishWishPros(Order order) {

        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCAHNGE, RabbitMQConstant.PRODUCT_SYNC_ROUTING_KEY, order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setCorrelationId(UUID.randomUUID().toString().replace("-", ""));
                return message;
            }
        });
    }

    @Override
    public void publishWishConfirms(Order order) {

        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCAHNGE, RabbitMQConstant.ORDER_SYNC_ROUTING_KEY, order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setCorrelationId(UUID.randomUUID().toString().replace("-", ""));
                return message;
            }
        });
    }
}
