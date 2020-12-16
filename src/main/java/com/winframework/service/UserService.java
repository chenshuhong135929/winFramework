package com.winframework.service;

import com.winframework.common.CommonResult;
import com.winframework.entity.User;

import java.util.concurrent.CompletableFuture;

public interface UserService {

  CompletableFuture<CommonResult<User> > selectByNameAndPassword(User user);
}
