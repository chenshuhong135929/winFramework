package com.winframework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winframework.common.CommonResult;
import com.winframework.entity.ProductionRecord;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface ProductionRecordService extends IService<ProductionRecord> {

  CompletableFuture<CommonResult> startProductionRecord(ProductionRecord productionRecord);

  CompletableFuture<CommonResult<List<ProductionRecord>>> selectProductionRecord( String factory);

  CompletableFuture<CommonResult< List<ProductionRecord>>> updateProductionRecords( List<ProductionRecord> productionRecords );

  CompletableFuture<CommonResult<List<ProductionRecord>>> selectWorkProductionRecord(String factory );

  CompletableFuture<CommonResult<List<ProductionRecord>>> selectStayProductionRecord( String factory);

  CompletableFuture<CommonResult> startWorkProductionRecord(Long id ,String machineCode);

  CompletableFuture<CommonResult> endWorkProductionRecord(Long id ,String machineCode);

}
