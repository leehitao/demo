package com.xrt.bzj.service.rocketmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.xrt.bzj.dao.entity.Order;
import com.xrt.bzj.dao.entity.Point;
import com.xrt.bzj.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description：监听消息处理类
 */
@Service
@Slf4j
public class PointProcessorImpl implements PointProcessor {

    @Autowired
    private PointService pointService;

    @Override
    @Transactional
    public boolean handle(MessageExt messageExt) {
        // 收到的body（消息体），字节类型，需转为String
        String result = new String(messageExt.getBody());
        try {
            log.info("接收到订单信息，开始计算积分");
            Order order = JSONObject.parseObject(result, Order.class);
            Point point = new Point();
            point.setOrderNo(order.getOrderNo());
            System.out.println(1 / 0);
            point.setPoint(10);
            pointService.save(point);
            log.info("完成积分计算");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}