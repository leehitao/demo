package com.xrt.bzj.dao.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xrt.bzj.dao.entity.Order;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.io.Serializable;

/**
 *  Mapper 接口
 *
 * @author lee
 * @since 2022-02-24
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {


}
