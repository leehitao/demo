package com.xrt.bzj.dao.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xrt.bzj.dao.entity.Product;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.io.Serializable;

/**
 *  Mapper 接口
 *
 * @author lee
 * @since 2022-03-08
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {


}
