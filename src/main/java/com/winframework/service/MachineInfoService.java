package com.winframework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winframework.common.CommonResult;
import com.winframework.entity.MachineInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MachineInfoService   extends IService<MachineInfo>  {

  /**
   * 获取设备下拉框数据
   * @return
   */
  CompletableFuture<CommonResult<List<MachineInfo>>> selectMachineInfo(String factory );



}
