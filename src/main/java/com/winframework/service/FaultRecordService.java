package com.winframework.service;

import com.winframework.common.CommonResult;
import com.winframework.entity.FaultRecord;
import com.winframework.entity.FaultVo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FaultRecordService {

  CompletableFuture< CommonResult<List<FaultRecord>>> save(FaultVo faultRecords);
}
