package com.wjd.mynote.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Data;

@Data
@ApiModel("用户实体类")
public class User {

    /**
     *  用户Id
     */
    @ApiModelProperty(value = "用户Id",example = "123_用户id")
    private Integer id;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户的姓名")
    private String name;

    @ApiModelProperty(value = "用户的密码")
    private String password;

    @ApiModelProperty(value = "用户角色")
    private String roleName;

}
