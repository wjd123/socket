package com.wjd.mynote.controller;

import com.wjd.mynote.dao.UserMapper;
import com.wjd.mynote.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wangjd
 */
@RestController
public class UserController {

    @Resource
    UserMapper userMapper;

    @GetMapping("/getUserList")
    public List<User> getUserList(){



        return userMapper.getAllUser();
    }

    @GetMapping("/getUserList2")
    public List<User> getUserList2(){
        return userMapper.getAllUser2();
    }

}
