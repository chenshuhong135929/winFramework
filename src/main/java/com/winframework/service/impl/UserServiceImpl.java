package com.winframework.service.impl;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winframework.common.CommonResult;
import com.winframework.entity.User;
import com.winframework.mapper.UserMapper;
import com.winframework.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther ChenShuHong
 * @Date 2020-12-16 9:56
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


  @Autowired
  UserMapper userMapper;


  @Override
  public CompletableFuture<CommonResult<User>> selectByNameAndPassword(User user) {
    CommonResult<User>result = new CommonResult<>();

    CompletableFuture<CommonResult<User>> future = CompletableFuture.supplyAsync(() -> {

      if(StrUtil.isEmpty(user.getName())){
        result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        result.setMessage("用户名称不能为空");
        return result;
      }
      if(StrUtil.isEmpty(user.getPassword())){
        result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        result.setMessage("用户密码不能为空");
        return result;
      }
      String base64Password = ComputeHash(user.getPassword(),user.getName() );
      User reuser = userMapper.selectByNameAndPassword(user.getName(), base64Password);
      if(StrUtil.isEmptyIfStr(reuser)){
        result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        result.setMessage("用户账号或密码错误！");
        return result;
      }
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("登录成功！");
      result.setData(reuser);
      return result ;
    });

    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("用户登录失败["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });


    return future;
  }


  public static String ComputeHash(String source, String key)
  {
    if (source == null)
    {
      return "";
    }
    String text = "abcdefghjklmnopqrstuvwxyz";
    if (source.length() < 0x1a)
    {
      source = source + text.substring(source.length());
    }

    byte[] inArray = new byte[0];
    try {
      inArray = source.getBytes("UTF-16LE");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    int length = inArray.length;
    if ((key == null) || (key.length() == 0))
    {
      key = "Encrypthejinhua";
    }

    byte[] bytes = new byte[0];
    try {
      bytes = key.getBytes("UTF-16LE");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    byte num2 = Convert.toByte(bytes.length);
    byte num3 = 2;
    byte index = 0;
    for (int i = 0; i < length; i++)
    {
      byte[] buffer3;
      Object ptr;
      byte num5 = (byte) (bytes[index] | num2);
      num5 = (byte) (num5 & num3);
      (buffer3 = inArray)[(int) (ptr = (Object) i)] = (byte) (buffer3[(int) ptr] ^ num5);
      num3 = (byte) (num3 + 1);
      if (num3 > 0xfd)
      {
        num3 = 2;
      }
      index = (byte) (index + 1);
      if (index >= num2)
      {
        index = 0;
      }
    }

    return Base64.encode(inArray);
  }


}
