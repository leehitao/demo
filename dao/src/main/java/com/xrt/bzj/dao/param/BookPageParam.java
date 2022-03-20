package com.xrt.bzj.dao.param;

import io.geekidea.springbootplus.generator.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 *  分页参数对象
 * </pre>
 *
 * @author lee
 * @date 2022-03-19
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "分页参数")
public class BookPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
