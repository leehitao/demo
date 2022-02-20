package com.xrt.bzj.dao.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("用户信息请求参数类")
public class AverageUser implements Serializable {

    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改时间")
    private Date updateDate;

    @ApiModelProperty("用户头像")
    private String headUrl;

    private static final long serialVersionUID = 1L;
}