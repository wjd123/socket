package com.wjd.mynote.controller;

import com.wjd.mynote.dto.Good;
import com.wjd.mynote.service.GoodService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/good")
@Api(tags = {"商品"})
@ApiOperation("商品 API")
public class GoodController {

    @Autowired
    GoodService goodService;

    @ApiOperation("发布商品")
    @PostMapping("/add")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "登录密码", required = true, dataType = "String")
    })
    public void releaseGoods(HttpServletRequest request,Good good) throws InterruptedException {

//        Good good = new Good();
        System.out.println(good.getId());
        System.out.println(good.getName());
        goodService.releaseGoods(request,good);
    }

    @ApiOperation("竞拍商品")
    @GetMapping("/bid")
    public boolean bid(@ApiParam(value = "用户id",example = "12") int id,@ApiParam(value = "id",example = "12") int buyerId, HttpServletRequest request){
        return goodService.bidGood(request,id,buyerId);
    }

    @ApiOperation("获取商品信息")
    @GetMapping("/get_all")
    public List<Good> getGoodList(HttpServletRequest request){
        return goodService.getGoodList(request);
    }

    /** 清空 */
    @ApiIgnore
    @GetMapping("/clean")
    public void clean(HttpServletRequest request){
        ArrayList<Good> goods = null;
        request.getServletContext().setAttribute("goods", null);
    }
}
