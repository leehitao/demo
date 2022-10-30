package com.xrt.bzj.web.controller;


import com.xrt.bzj.dao.entity.Product;
import com.xrt.bzj.dao.param.ProductPageParam;
import com.xrt.bzj.service.ProductService;
import com.xrt.bzj.service.handle.MsgHandlerFactory;
import io.geekidea.springbootplus.generator.common.api.ApiResult;
import io.geekidea.springbootplus.generator.common.controller.BaseController;
import io.geekidea.springbootplus.generator.core.pagination.Paging;
import io.geekidea.springbootplus.generator.core.validator.groups.Add;
import io.geekidea.springbootplus.generator.core.validator.groups.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *  控制器
 *
 * @author lee
 * @since 2022-03-08
 */
@Slf4j
@RestController
@RequestMapping("/product")
@Api(value = "API", tags = {"商品API"})
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    MsgHandlerFactory msgHandlerFactory;

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加", response = ApiResult.class)
    public ApiResult<Boolean> addProduct(@Validated(Add.class) @RequestBody Product product) throws Exception {
        boolean flag = productService.saveProduct(product);
        return ApiResult.result(flag);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", response = ApiResult.class)
    public ApiResult<Boolean> updateProduct(@Validated(Update.class) @RequestBody Product product) throws Exception {
        boolean flag = productService.updateProduct(product);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除", response = ApiResult.class)
    public ApiResult<Boolean> deleteProduct(@PathVariable("id") Long id) throws Exception {
        boolean flag = productService.deleteProduct(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取详情
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "详情", response = Product.class)
    public ApiResult<Product> getProduct(@PathVariable("id") Long id) throws Exception {
        Product product = productService.getById(id);
        return ApiResult.ok(product);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @ApiOperation(value = "分页列表", response = Product.class)
    public ApiResult<Paging<Product>> getProductPageList(@Validated @RequestBody ProductPageParam productPageParam) throws Exception {
        Paging<Product> paging = productService.getProductPageList(productPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 分页列表
     */
    @PostMapping("/test")
    @ApiOperation(value = "分页列表", response = Product.class)
    public void test() throws Exception {

        msgHandlerFactory.createHandler("1").send();

    }

}

