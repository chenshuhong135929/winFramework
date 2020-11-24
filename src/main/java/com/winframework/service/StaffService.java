package com.winframework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winframework.common.CommonResult;
import com.winframework.entity.ProductInfo;
import com.winframework.entity.Staff;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface StaffService  extends IService<Staff> {

  /**
   * 人员下啦
   * @return
   */
  CompletableFuture<CommonResult<List<Staff>>> selectStaff();

}
