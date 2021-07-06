package com.winframework.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winframework.common.CommonResult;
import com.winframework.entity.MachineUseStatus;
import com.winframework.entity.ProductInfo;
import com.winframework.entity.ProductionRecord;
import com.winframework.mapper.FaultRecordMapper;
import com.winframework.mapper.MachineInfoMapper;
import com.winframework.mapper.MachineUseStatusMapper;
import com.winframework.mapper.ProductionRecordMapper;
import com.winframework.service.MachineInfoService;
import com.winframework.service.ProductionRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

  @Autowired
  FaultRecordMapper faultRecordMapper;

  @Override
  @Transactional
  public CompletableFuture<CommonResult> startProductionRecord(ProductionRecord productionRecord) {
    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult> future = CompletableFuture.supplyAsync(() -> {
      if( productionRecordMapper.countProductionRecord(productionRecord.getMachineCode())>0){
        result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        result.setMessage("该机台已安排生产，是否强制结束，重新安排？。");
      }else {
        productionRecord.setClasses(classesSet());
        productionRecord.setOperating(productionRecord.getIsAuto() == true ? 1l : 0l);
        machineInfoMapper.updateStatus(productionRecord.getMachineCode(),productionRecord.getProName(), productionRecord.getIsAuto() == true ? "1" : "4");
        machineUseStatusMapper.updateEndTime(productionRecord.getMachineCode());
        MachineUseStatus machineUseStatus = new MachineUseStatus();
        machineUseStatus.setMachineCode(productionRecord.getMachineCode());
        machineUseStatus.setProName(productionRecord.getProName());
        if (productionRecord.getIsAuto()) {
          machineUseStatus.setStatus(1);
        } else {
          machineUseStatus.setStatus(4);
        }
        productionRecord.setIsFinish(false);
        productionRecord.setIsProing(false);
        productionRecordMapper.addProductionRecord(productionRecord);
        machineUseStatus.setProRecordID(productionRecordMapper.selectByMachineCode(productionRecord.getMachineCode()));
        machineUseStatusMapper.addMachineUseStatus(machineUseStatus);
        result.setCode(HttpStatus.HTTP_OK);
        result.setMessage("调机成功。");
      }
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
  public CompletableFuture<CommonResult<List<ProductionRecord>>> selectProductionRecord(String factory) {

    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<ProductionRecord>>> future = CompletableFuture.supplyAsync(() -> {


      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("显示调机列表成功。");
      result.setData(productionRecordMapper.selectProductionRecord(factory));
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
  public CompletableFuture<CommonResult< List<ProductionRecord>>> updateProductionRecords(List<ProductionRecord> productionRecords) {
    CommonResult< List<ProductionRecord>> result = new CommonResult();
    CompletableFuture<CommonResult< List<ProductionRecord>>> future = CompletableFuture.supplyAsync(() -> {
      List<ProductionRecord> reProducionRecords=new ArrayList<>();
      for( ProductionRecord p : productionRecords){
        MachineUseStatus machineUseStatus = new MachineUseStatus();
        machineUseStatusMapper.updateEndTime(p.getMachineCode());
        if(p.getOperating()==1){
          machineInfoMapper.updateStatus(p.getMachineCode(),p.getProName(),"1");
          machineUseStatus.setStatus(1);
        }
        if(p.getOperating()==2){
          machineInfoMapper.updateStatus(p.getMachineCode(),null,"2");
          p.setIsFinish(true);
          machineUseStatus.setStatus(2);
        }
        if(!p.getIsError()){
          machineInfoMapper.updateStatus(p.getMachineCode(),p.getProName(),"1");
          machineUseStatus.setStatus(1);
        }else {
          faultRecordMapper.updateFaultRecord(p.getId());
        }

        machineUseStatus.setMachineCode(p.getMachineCode());
        machineUseStatus.setProName(p.getProName());
        machineUseStatus.setProRecordID(p.getId());
        machineUseStatusMapper.addMachineUseStatus(machineUseStatus);
        productionRecordMapper.updateProductionRecord(p);
        reProducionRecords.add(productionRecordMapper.selectByIdProductionRecord(p.getId(),  p.getFactory()))  ;
      }
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("更新调机列表成功。");
      result.setData(reProducionRecords);
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

  @Override
  public CompletableFuture<CommonResult<List<ProductionRecord>>> selectWorkProductionRecord(String factory) {

    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<ProductionRecord>>> future = CompletableFuture.supplyAsync(() -> {


      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("显示生产中列表成功。");
      result.setData(productionRecordMapper.selectWorkProductionRecord(factory));
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("显示生产中列表出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }

  @Override
  public CompletableFuture<CommonResult<List<ProductionRecord>>> selectStayProductionRecord(String factory) {

    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<ProductionRecord>>> future = CompletableFuture.supplyAsync(() -> {


      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("显示待生产列表成功。");
      result.setData(productionRecordMapper.selectStayProductionRecord(factory));
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("显示待生产列表出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }

  @Override
  public CompletableFuture<CommonResult> startWorkProductionRecord(Long id,String machineCode) {


    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult > future = CompletableFuture.supplyAsync(() -> {

      if(productionRecordMapper.selectByIdWorkProductionRecordCount(machineCode)>0){
        result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        result.setMessage("机台有计划正在生产中，请结束后再操作。");
        return result;
      }

      productionRecordMapper.updateByIdWorkIsProing(id);
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("机台计划生产中。");
      return result;
    });
    future= future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("机台计划生产中出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;

  }

  @Override
  public CompletableFuture<CommonResult> endWorkProductionRecord(Long id, String machineCode) {

    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult > future = CompletableFuture.supplyAsync(() -> {

      productionRecordMapper.endWorkProductionRecord(id);
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("机台结束生产。");
      return result;
    });
    future= future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("机台结束生产出错["+e.getMessage() +"]");
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
    DateTime dayStartTime = DateUtil.parse("6:30");
    DateTime dayEndTime = DateUtil.parse("18:30");
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
