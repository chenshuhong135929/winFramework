package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper  extends BaseMapper<User> {

    User selectByNameAndPassword(@Param("name")  String  name,@Param("password")  String  password);

}
