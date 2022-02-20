package com.xrt.bzj.dao.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.dao.vo
 * @date 2022/2/20 18:13
 */
@Data
@ApiModel("用户信息类")
public class AverageUserVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户头像")
    private String headUrl;


}
