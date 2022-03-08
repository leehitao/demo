package com.xrt.bzj.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xrt.bzj.dao.entity.Product;
import com.xrt.bzj.dao.mapper.ProductMapper;
import com.xrt.bzj.dao.param.ProductPageParam;
import com.xrt.bzj.service.ProductService;
import io.geekidea.springbootplus.generator.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.generator.core.pagination.Paging;
import io.geekidea.springbootplus.generator.core.pagination.PageInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  服务实现类
 *
 * @author lee
 * @since 2022-03-08
 */
@Slf4j
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveProduct(Product product) throws Exception {
        return super.save(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateProduct(Product product) throws Exception {
        return super.updateById(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteProduct(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<Product> getProductPageList(ProductPageParam productPageParam) throws Exception {
        Page<Product> page = new PageInfo<>(productPageParam);
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        IPage<Product> iPage = productMapper.selectPage(page, wrapper);
        return new Paging<Product>(iPage);
    }

}
