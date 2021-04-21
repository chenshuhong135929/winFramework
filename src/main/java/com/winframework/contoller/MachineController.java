package com.winframework.contoller;

import com.winframework.common.CommonResult;
import com.winframework.entity.MachineInfo;
import com.winframework.service.MachineInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 9:35
 */
@RestController
@RequestMapping("machineInfo")
@Api(value = "MachineController机台接口")
public class MachineController {

  @Autowired
  MachineInfoService machineInfoService;



  @ApiOperation(value = "机台下拉列表")
  @PostMapping("selectMachineInfo")
  public CommonResult<List<MachineInfo>> selectMachineInfo(String factory ) throws Exception {

    return machineInfoService.selectMachineInfo(factory).get();
  }


}
