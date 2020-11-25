package com.winframework.contoller;

import com.winframework.common.CommonResult;
import com.winframework.entity.FaultInfo;
import com.winframework.entity.FaultVo;
import com.winframework.entity.ProductionRecord;
import com.winframework.entity.Staff;
import com.winframework.service.FaultInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 17:33
 */
@RestController
@RequestMapping("faultInfo")
@Api(value = "FaultInfoController机台故障接口")
public class FaultInfoController {

  @Autowired
  FaultInfoService faultInfoService;
  @ApiOperation(value = "故障列表")
  @PostMapping("selectFaultInfo")
  public CommonResult<List<FaultInfo>> selectFaultInfo() throws Exception {

    return faultInfoService.selectFaultInfo().get();
  }



  @ApiOperation(value = "故障报告")
  @PostMapping("faultReport")
  public CommonResult faultReport(@RequestBody FaultVo faultVo) throws Exception {

    return faultInfoService.faultReport(faultVo).get();
  }

}
