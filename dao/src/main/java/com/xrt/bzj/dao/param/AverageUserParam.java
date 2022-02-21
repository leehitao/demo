package com.xrt.bzj.dao.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.dao.param
 * @date 2022/2/20 18:14
 */
@Data
@Accessors(chain = true)
@ApiModel("用户信息请求参数类")
@AllArgsConstructor
@NoArgsConstructor
public class AverageUserParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private Integer id;

}
