package com.wjd.mynote.dao;

import com.wjd.mynote.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: wangjd
 * @Date: 2021/4/10 0:30
 */
@Mapper
public interface UserMapper {

    List<User> getAllUser();

    @Select("Select * from user")
    List<User> getAllUser2();

}
