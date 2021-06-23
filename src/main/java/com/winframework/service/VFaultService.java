package com.winframework.service;

import com.winframework.common.CommonResult;
import com.winframework.entity.VFault;


import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface VFaultService {
  /**
   * 获取故障列表
   * @param proRecordID
   * @return
   */
  CompletableFuture<CommonResult< List<VFault> >> selectByProRecordID(String proRecordID);
}
