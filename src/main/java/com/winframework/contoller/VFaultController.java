package com.winframework.contoller;

import com.winframework.common.CommonResult;
import com.winframework.entity.VFault;
import com.winframework.service.VFaultService;
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
 * @Date 2021-06-23 14:58
 */
@RestController
@RequestMapping("vFault")
@Api(value = "vFault故障视图")
public class VFaultController {

  @Autowired
  VFaultService vFaultService;

  @ApiOperation(value = "故障列表")
  @PostMapping("selectByNameAndPassword")
  public CommonResult<List<VFault>> logon(String proRecordID) throws Exception {

    return vFaultService.selectByProRecordID(proRecordID).get();
  }


}
