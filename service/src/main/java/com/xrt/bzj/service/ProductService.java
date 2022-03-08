package com.xrt.bzj.service;


import com.xrt.bzj.dao.entity.Product;
import com.xrt.bzj.dao.param.ProductPageParam;
import io.geekidea.springbootplus.generator.common.service.BaseService;
import io.geekidea.springbootplus.generator.core.pagination.Paging;

/**
 *  服务类
 *
 * @author lee
 * @since 2022-03-08
 */
public interface ProductService extends BaseService<Product> {

    /**
     * 保存
     *
     * @param product
     * @return
     * @throws Exception
     */
    boolean saveProduct(Product product) throws Exception;

    /**
     * 修改
     *
     * @param product
     * @return
     * @throws Exception
     */
    boolean updateProduct(Product product) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteProduct(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param productQueryParam
     * @return
     * @throws Exception
     */
    Paging<Product> getProductPageList(ProductPageParam productPageParam) throws Exception;

}
