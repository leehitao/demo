package com.xrt.bzj.dao.param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
public class AverageUserParam implements Serializable {
    private static final long serialVersionUID = 1L;

    public AverageUserParam() {
    }

    @ApiModelProperty("用户ID")
    private Integer id;

}
