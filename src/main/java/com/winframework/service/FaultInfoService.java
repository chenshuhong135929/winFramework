package com.winframework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winframework.common.CommonResult;
import com.winframework.entity.FaultInfo;
import com.winframework.entity.FaultVo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FaultInfoService extends IService<FaultInfo> {

  CompletableFuture<CommonResult<List<FaultInfo>>> selectFaultInfo();

  CompletableFuture<CommonResult> faultReport( FaultVo faultVo);

}
