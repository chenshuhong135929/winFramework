package com.winframework.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winframework.common.CommonResult;
import com.winframework.entity.MachineInfo;
import com.winframework.mapper.MachineInfoMapper;
import com.winframework.service.MachineInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 9:45
 */
@Service
@Slf4j
public class MachineInfoServiceImpl extends ServiceImpl<MachineInfoMapper, MachineInfo>  implements MachineInfoService {

  @Autowired
  MachineInfoMapper machineInfoMapper;


  @Override
  public CompletableFuture<CommonResult<List<MachineInfo>>> selectMachineInfo(String factory ) {
    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<MachineInfo>>> future = CompletableFuture.supplyAsync(() -> {
        result.setCode(HttpStatus.HTTP_OK);
        result.setMessage("获取设备成功。");
        result.setData( machineInfoMapper.selectMachineInfo(factory));
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("获取设备下拉设备出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }

}
