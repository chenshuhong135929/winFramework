package com.winframework.service.impl;

import cn.hutool.http.HttpStatus;
import com.winframework.common.CommonResult;
import com.winframework.entity.FaultRecord;
import com.winframework.mapper.FaultRecordMapper;
import com.winframework.service.FaultRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther ChenShuHong
 * @Date 2021-06-23 15:42
 */
@Slf4j
@Service
public class FaultRecordServiceImpl implements FaultRecordService {


  @Autowired
  FaultRecordMapper faultRecordMapper;

  @Override
  public CompletableFuture<CommonResult<List<FaultRecord>>> save(List<FaultRecord> faultRecords) {
    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<FaultRecord>>> future = CompletableFuture.supplyAsync(() -> {

      faultRecords.stream().forEach(x->{
        x.setRecordTime(new Timestamp( new Date().getTime()));
        faultRecordMapper.addFaultRecord(x);
      });

      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("添加故障记录成功。");
      result.setData( faultRecords);
      return result;
    });
    future=future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("添加故障记录出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }
}
