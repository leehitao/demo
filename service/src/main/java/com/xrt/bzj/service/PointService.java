package com.xrt.bzj.service;


import com.xrt.bzj.dao.entity.Point;
import com.xrt.bzj.dao.param.PointPageParam;
import io.geekidea.springbootplus.generator.common.service.BaseService;
import io.geekidea.springbootplus.generator.core.pagination.Paging;

/**
 * 服务类
 *
 * @author lee
 * @since 2022-03-11
 */
public interface PointService extends BaseService<Point> {

    /**
     * 保存
     *
     * @param point
     * @return
     * @throws Exception
     */
    boolean savePoint(Point point) throws Exception;

    /**
     * 修改
     *
     * @param point
     * @return
     * @throws Exception
     */
    boolean updatePoint(Point point) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deletePoint(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param pointQueryParam
     * @return
     * @throws Exception
     */
    Paging<Point> getPointPageList(PointPageParam pointPageParam) throws Exception;

}
