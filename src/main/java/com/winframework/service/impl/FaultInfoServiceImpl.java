package com.winframework.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winframework.common.CommonResult;
import com.winframework.entity.*;
import com.winframework.mapper.FaultInfoMapper;
import com.winframework.mapper.FaultRecordMapper;
import com.winframework.mapper.MachineUseStatusMapper;
import com.winframework.mapper.ProductionRecordMapper;
import com.winframework.service.FaultInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
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

  @Autowired
  ProductionRecordMapper productionRecordMapper;

  @Autowired
  FaultRecordMapper faultRecordMapper;

  @Autowired
  MachineUseStatusMapper machineUseStatusMapper;

  @Override
  public CompletableFuture<CommonResult<List<Map<String,List<FaultInfo>>>>> selectFaultInfo() {

    CommonResult<List<Map<String,List<FaultInfo>>>> result = new CommonResult();

    List list = new ArrayList();
    CompletableFuture<CommonResult<List<Map<String,List<FaultInfo>>>>>future = CompletableFuture.supplyAsync(() -> {
      List<String> faulTypes = faultInfoMapper.selectByFaultTypeFaultInfo();
      faulTypes.stream().forEach((x)->{
        Map<String,List<FaultInfo>> map= new HashMap<>();
        List<FaultInfo> faultInfos = faultInfoMapper.selectFaultInfo(x);
        map.put(x,faultInfos);
        list.add(map);
      });
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("获取机台故障列表成功。");
      result.setData(list );
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

  @Override
  public CompletableFuture<CommonResult> faultReport(FaultVo faultVo) {

    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult> future = CompletableFuture.supplyAsync(() -> {
      productionRecordMapper.updateProductionRecordIsError(true,faultVo.getProReID());
      for(String faultCode :faultVo.getFaultCodes()){
        FaultRecord faultRecord = new FaultRecord();
        faultRecord.setFaultCode(faultCode);
        faultRecord.setMusId(faultVo.getMusId());
        faultRecord.setProReID(faultVo.getProReID());
        faultRecord.setMachineCode(faultVo.getMachineCode());
        faultRecord.setRecordTime(new Timestamp( new Date().getTime()));
        faultRecordMapper.addFaultRecord(faultRecord);
      }
      machineUseStatusMapper.updateEndTime(faultVo.getMachineCode());
      MachineUseStatus  machineUseStatus =new MachineUseStatus();
      machineUseStatus.setProName(faultVo.getProName());
      machineUseStatus.setMachineCode(faultVo.getMachineCode());
      machineUseStatus.setStatus(3);
      machineUseStatus.setBeginTime(new Timestamp(new Date().getTime()));
      machineUseStatus.setProRecordID(faultVo.getProReID());
      machineUseStatusMapper.addMachineUseStatus(machineUseStatus);
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("添加故障信息成功。");
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("添加故障信息出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }
}
