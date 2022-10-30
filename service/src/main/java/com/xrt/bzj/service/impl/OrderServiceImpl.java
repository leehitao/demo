package com.xrt.bzj.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrt.bzj.dao.entity.Order;
import com.xrt.bzj.dao.mapper.OrderMapper;
import com.xrt.bzj.dao.param.OrderPageParam;
import com.xrt.bzj.service.OrderService;
import io.geekidea.springbootplus.generator.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.generator.core.pagination.PageInfo;
import io.geekidea.springbootplus.generator.core.pagination.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  服务实现类
 *
 * @author lee
 * @since 2022-02-24
 */
@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrder(Order order) throws Exception {
//        System.out.println(1 / 0);
        return super.save(order);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateOrder(Order order) throws Exception {
        return super.updateById(order);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteOrder(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<Order> getOrderPageList(OrderPageParam orderPageParam) throws Exception {
        Page<Order> page = new PageInfo<>(orderPageParam, OrderItem.desc(getLambdaColumn(Order::getCreateTime)));
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        IPage<Order> iPage = orderMapper.selectPage(page, wrapper);
        return new Paging<Order>(iPage);
    }

}
