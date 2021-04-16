package com.wjd.mynote.controller;

import com.wjd.mynote.dao.UserMapper;
import com.wjd.mynote.dto.User;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wangjd
 */
@RestController("user")
@RequestMapping("/user")
@Api(tags = {"用户"})
@ApiOperation("用户 API")
public class UserController {

    @Resource
    UserMapper userMapper;

    /**
     * @param userId
     * @return User
     */
    @ApiOperation("查询用户信息")
    @GetMapping("/get")
    @ApiResponses({
            @ApiResponse(code = 404, message = "页面找不到"),
    @ApiResponse(code = 200, message = "成功", response = User.class)})

    public User getUserInfo(@ApiParam(value = "用户id",example = "123") @RequestParam int userId){

        return null;
    }


    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public int updateUserInfo(@ApiParam(name = "body", type = "object") @RequestBody User user){
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        return 1;
    }

}
