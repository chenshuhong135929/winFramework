package com.winframework.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winframework.common.CommonResult;
import com.winframework.entity.FaultInfo;
import com.winframework.entity.Staff;
import com.winframework.mapper.FaultInfoMapper;
import com.winframework.service.FaultInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 17:28
 */
@Service
@Slf4j
public class FaultInfoServiceImpl extends ServiceImpl<FaultInfoMapper, FaultInfo> implements FaultInfoService {

  @Autowired
  FaultInfoMapper faultInfoMapper;


  @Override
  public CompletableFuture<CommonResult<List<FaultInfo>>> selectFaultInfo() {

    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<FaultInfo>>> future = CompletableFuture.supplyAsync(() -> {
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("获取机台故障列表成功。");
      result.setData( faultInfoMapper.selectFaultInfo());
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("获取机台故障列表出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }
}
