package com.xrt.bzj.web.controller;


import com.alibaba.fastjson.JSON;
import com.xrt.bzj.dao.entity.Order;
import com.xrt.bzj.dao.param.OrderPageParam;
import com.xrt.bzj.service.OrderService;
import io.geekidea.springbootplus.generator.common.api.ApiResult;
import io.geekidea.springbootplus.generator.common.controller.BaseController;
import io.geekidea.springbootplus.generator.core.pagination.Paging;
import io.geekidea.springbootplus.generator.core.validator.groups.Add;
import io.geekidea.springbootplus.generator.core.validator.groups.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *  控制器
 *
 * @author lee
 * @since 2022-02-24
 */
@Slf4j
@RestController
@RequestMapping("/order")
@Api(value = "API", tags = {"订单API"})
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;


    //    @Autowired
//    @Qualifier("TransactionMQProducer")
    TransactionMQProducer producer;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addOrder(@Validated(Add.class) @RequestBody Order order) throws Exception {

        boolean flag = orderService.saveOrder(order);
        return ApiResult.result(flag);
    }


    /**
     * 模拟创建订单
     */
    @PostMapping("/createOrder")
    @ApiOperation(value = "创建订单", response = ApiResult.class)
    public ApiResult<Boolean> createOrder(@Validated(Add.class) @RequestBody Order order) throws Exception {
        Message message = new Message("topic2020", "order", JSON.toJSONString(order).getBytes());
        message.putUserProperty("age", "20");
//        message.setDelayTimeLevel(3);  事务消息不支持延时
        producer.sendMessageInTransaction(message, null);
        return ApiResult.result(true);
    }


    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateOrder(@Validated(Update.class) @RequestBody Order order) throws Exception {
        boolean flag = orderService.updateOrder(order);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteOrder(@PathVariable("id") Long id) throws Exception {
        boolean flag = orderService.deleteOrder(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "详情", response = Order.class)
    public ApiResult<Order> getOrder(@PathVariable("id") Long id) throws Exception {
        Order order = orderService.getById(id);
        return ApiResult.ok(order);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "分页列表", response = Order.class)
    public ApiResult<Paging<Order>> getOrderPageList(@Validated @RequestBody OrderPageParam orderPageParam) throws Exception {
        Paging<Order> paging = orderService.getOrderPageList(orderPageParam);
        return ApiResult.ok(paging);
    }


}

