package com.winframework.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.winframework.common.CommonResult;
import com.winframework.entity.VFault;
import com.winframework.mapper.VFaultMapper;
import com.winframework.service.VFaultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther ChenShuHong
 * @Date 2021-06-23 15:00
 */
@Service
@Slf4j
public class VFaultServiceImpl implements VFaultService {

  @Autowired
  VFaultMapper vFaultMapper;

  @Override
  public CompletableFuture<CommonResult<List<VFault>>> selectByProRecordID(String proRecordID) {
    CommonResult<List<VFault>>result = new CommonResult<>();

    CompletableFuture<CommonResult<List<VFault>>> future = CompletableFuture.supplyAsync(() -> {


      if(StrUtil.isEmpty(proRecordID)){
        result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        result.setMessage("调机Id不能为空");
        return result;
      }

      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("故障列表获取成功！");
      result.setData(vFaultMapper.selectByProRecordID(proRecordID));
      return result ;
    });

    future = future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("故障列表获取[" + e.getMessage() + "]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });


    return future;
  }
}
