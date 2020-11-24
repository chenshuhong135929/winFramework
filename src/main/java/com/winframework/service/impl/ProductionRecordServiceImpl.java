package com.winframework.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winframework.common.CommonResult;
import com.winframework.entity.MachineUseStatus;
import com.winframework.entity.ProductInfo;
import com.winframework.entity.ProductionRecord;
import com.winframework.mapper.MachineInfoMapper;
import com.winframework.mapper.MachineUseStatusMapper;
import com.winframework.mapper.ProductionRecordMapper;
import com.winframework.service.MachineInfoService;
import com.winframework.service.ProductionRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 13:52
 */
@Service
@Slf4j
public class ProductionRecordServiceImpl extends ServiceImpl<ProductionRecordMapper, ProductionRecord> implements ProductionRecordService {


  @Autowired
  ProductionRecordMapper productionRecordMapper;

  @Autowired
  MachineInfoMapper machineInfoMapper;

  @Autowired
  MachineUseStatusMapper machineUseStatusMapper;

  @Override
  @Transactional
  public CompletableFuture<CommonResult> startProductionRecord(ProductionRecord productionRecord) {
    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult> future = CompletableFuture.supplyAsync(() -> {
      productionRecord.setClasses(classesSet());
      productionRecord.setOperating(productionRecord.getIsAuto()==true? 1l:0l);
      machineInfoMapper.updateStatus(productionRecord.getMachineCode(),productionRecord.getIsAuto()==true?"1":"4");
      machineUseStatusMapper.updateEndTime(productionRecord.getMachineCode());
      MachineUseStatus machineUseStatus = new MachineUseStatus();
      machineUseStatus.setMachineCode(productionRecord.getMachineCode());
      machineUseStatus.setProName(productionRecord.getProName());
      if(productionRecord.getIsAuto()){
        machineUseStatus.setStatus(1);
      }else {
        machineUseStatus.setStatus(4);
      }
      productionRecord.setIsFinish(false);
      productionRecordMapper.addProductionRecord(productionRecord);
      machineUseStatusMapper.addMachineUseStatus(machineUseStatus);
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("调机成功。");

      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("调机出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }

  @Override
  public CompletableFuture<CommonResult<List<ProductionRecord>>> selectProductionRecord() {

    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<ProductionRecord>>> future = CompletableFuture.supplyAsync(() -> {


      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("显示调机列表成功。");
      result.setData(productionRecordMapper.selectProductionRecord());
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("显示调机列表出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }

  @Override
  @Transactional
  public CompletableFuture<CommonResult> updateProductionRecords(List<ProductionRecord> productionRecords) {
    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult> future = CompletableFuture.supplyAsync(() -> {

      for( ProductionRecord p : productionRecords){
        MachineUseStatus machineUseStatus = new MachineUseStatus();
        machineUseStatusMapper.updateEndTime(p.getMachineCode());
        if(p.getOperating()==1){
          machineUseStatus.setStatus(1);
        }
        if(p.getOperating()==2){
          p.setIsFinish(true);
          machineUseStatus.setStatus(2);
        }
        if(!p.getIsError()){
          machineUseStatus.setStatus(1);
        }

        machineUseStatus.setMachineCode(p.getMachineCode());
        machineUseStatus.setProName(p.getProName());

        machineUseStatusMapper.addMachineUseStatus(machineUseStatus);
        productionRecordMapper.updateProductionRecord(p);
      }
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("更新调机列表成功。");
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("更新调机列表出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }


  /**
   * 获取当前时间班别
   * @return
   */
  public String classesSet()  {

    DateTime  currentTime = DateUtil.date();
    DateTime dayStartTime = DateUtil.parse("7:15");
    DateTime dayEndTime = DateUtil.parse("19:15");
    if(currentTime.after(dayStartTime)||currentTime.before(dayEndTime) ){
      log.info("调机白班");
      return "白班" ;
    }
    if(currentTime.after(dayEndTime)||currentTime.before(dayStartTime) ){
      log.info("调机晚班");
      return "晚班";
    }
    return null;
  }
}
