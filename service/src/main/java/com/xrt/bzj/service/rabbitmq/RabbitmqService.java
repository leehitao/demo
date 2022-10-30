package com.xrt.bzj.service.rabbitmq;

import com.xrt.bzj.dao.entity.Order;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.mq
 * @date 2021/7/1 22:27
 */
public interface RabbitmqService {
    void publish(Order order);
}
