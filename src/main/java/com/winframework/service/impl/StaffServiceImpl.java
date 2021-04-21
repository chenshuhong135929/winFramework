package com.winframework.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winframework.common.CommonResult;
import com.winframework.entity.ProductInfo;
import com.winframework.entity.Staff;
import com.winframework.mapper.StaffMapper;
import com.winframework.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 11:27
 */
@Service
@Slf4j
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService{

  @Autowired
  StaffMapper staffMapper;


  @Override
  public CompletableFuture<CommonResult<List<Staff>>> selectStaff(String factory) {
    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<Staff>>> future = CompletableFuture.supplyAsync(() -> {
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("获取员工成功。");
      result.setData( staffMapper.selectStaff(factory));
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("获取人员下拉设备出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }
}
