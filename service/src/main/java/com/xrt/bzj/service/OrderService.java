package com.xrt.bzj.service;


import com.xrt.bzj.dao.entity.Order;
import com.xrt.bzj.dao.param.OrderPageParam;
import io.geekidea.springbootplus.generator.common.service.BaseService;
import io.geekidea.springbootplus.generator.core.pagination.Paging;

/**
 *  服务类
 *
 * @author lee
 * @since 2022-02-24
 */
public interface OrderService extends BaseService<Order> {

    /**
     * 保存
     *
     * @param order
     * @return
     * @throws Exception
     */
    boolean saveOrder(Order order) throws Exception;

    /**
     * 修改
     *
     * @param order
     * @return
     * @throws Exception
     */
    boolean updateOrder(Order order) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteOrder(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param orderQueryParam
     * @return
     * @throws Exception
     */
    Paging<Order> getOrderPageList(OrderPageParam orderPageParam) throws Exception;

}
