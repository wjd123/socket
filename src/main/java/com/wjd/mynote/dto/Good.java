package com.wjd.mynote.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class Good {

    @ApiParam(value = "商品",example = "123")
    @ApiModelProperty(value = "商品的Id",example = "123")
    int id;

    @ApiParam(value = "商品",example = "123")
    @ApiModelProperty(value = "商品的Id")
    String name;

    @ApiParam(value = "商品",example = "123")
    @ApiModelProperty(value = "商品的Id")
    Date expiredTime;

    @ApiParam(value = "商品",example = "123")
    @ApiModelProperty(value = "商品的Id",example = "123")
    int time;

    @ApiParam(value = "商品",example = "123")
    @ApiModelProperty(value = "商品的Id",example = "123")
    int sellerId;

    @ApiParam(value = "商品",example = "123")
    @ApiModelProperty(value = "商品的Id",example = "123")
    int buyerId;

}
