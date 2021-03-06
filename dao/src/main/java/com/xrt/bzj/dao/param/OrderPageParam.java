package com.xrt.bzj.dao.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.geekidea.springbootplus.generator.core.pagination.BasePageOrderParam;

/**
 * <pre>
 *  分页参数对象
 * </pre>
 *
 * @author lee
 * @date 2022-02-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "分页参数")
public class OrderPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
