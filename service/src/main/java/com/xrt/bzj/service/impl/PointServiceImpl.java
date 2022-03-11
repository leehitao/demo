package com.xrt.bzj.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrt.bzj.dao.entity.Point;
import com.xrt.bzj.dao.mapper.PointMapper;
import com.xrt.bzj.dao.param.PointPageParam;
import com.xrt.bzj.service.PointService;
import io.geekidea.springbootplus.generator.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.generator.core.pagination.PageInfo;
import io.geekidea.springbootplus.generator.core.pagination.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现类
 *
 * @author lee
 * @since 2022-03-11
 */
@Slf4j
@Service
public class PointServiceImpl extends BaseServiceImpl<PointMapper, Point> implements PointService {

    @Autowired
    private PointMapper pointMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean savePoint(Point point) throws Exception {
        return super.save(point);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updatePoint(Point point) throws Exception {
        return super.updateById(point);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deletePoint(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<Point> getPointPageList(PointPageParam pointPageParam) throws Exception {
        Page<Point> page = new PageInfo<>(pointPageParam, OrderItem.desc(getLambdaColumn(Point::getCreateTime)));
        LambdaQueryWrapper<Point> wrapper = new LambdaQueryWrapper<>();
        IPage<Point> iPage = pointMapper.selectPage(page, wrapper);
        return new Paging<Point>(iPage);
    }

}
