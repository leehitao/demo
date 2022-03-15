package com.xrt.bzj.service.rocketmq.producer;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xrt.bzj.dao.entity.Order;
import com.xrt.bzj.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: lee
 * @Date: 2022/3/11 11:22
 * @Version 1.0
 */
@Component
@Slf4j
public class ProductListen implements TransactionListener {

	@Autowired
	private OrderService orderService;

	@Override
	public LocalTransactionState executeLocalTransaction(Message message, Object o) {
		LocalTransactionState state;
		try {
			log.info("开始订单本地事务");
			String body = new String(message.getBody());
			Order order = JSONObject.parseObject(body, Order.class);
			orderService.saveOrder(order);
			log.info("完成订单本地事务");
			state = LocalTransactionState.COMMIT_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
			state = LocalTransactionState.ROLLBACK_MESSAGE;
		}
		return state;
	}

	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
		log.info("本地事务回调: ");
		String body = new String(messageExt.getBody());
		Order orderParam = JSONObject.parseObject(body, Order.class);
		String orderNo = orderParam.getOrderNo();
		log.info("orderNo: " + orderNo);
		LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(Order::getOrderNo, orderNo);
		Order order = orderService.getOne(wrapper);
		if (order == null) {
			log.info("回调检测结果: " + "fail");
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}
		log.info("回调检测结果: " + "success");
		return LocalTransactionState.COMMIT_MESSAGE;
	}
}
