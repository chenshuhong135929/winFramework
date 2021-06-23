package com.winframework.service;

import com.winframework.common.CommonResult;
import com.winframework.entity.FaultRecord;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FaultRecordService {

  CompletableFuture< CommonResult<List<FaultRecord>>> save(List<FaultRecord> faultRecords);
}
