package com.winframework.contoller;

import com.winframework.common.CommonResult;
import com.winframework.entity.User;
import com.winframework.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther ChenShuHong
 * @Date 2020-12-16 9:54
 */
@RestController
@RequestMapping("user")
@Api(value = "UserController用户接口")
public class UserContoller {

  @Autowired
  UserService userService;

  @ApiOperation(value = "用户登录")
  @PostMapping("selectByNameAndPassword")
  public CommonResult<User> logon(@RequestBody User user) throws Exception {

    return userService.selectByNameAndPassword(user).get();
  }
}
