package com.winframework.contoller;

import com.winframework.common.CommonResult;
import com.winframework.entity.FaultRecord;
import com.winframework.service.FaultRecordService;
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
 * @Date 2021-06-23 15:44
 */
@RestController
@RequestMapping("faultRecord")
@Api(value = "FaultInfoController机台故障记录接口")
public class FaultRecordController {

  @Autowired
  FaultRecordService faultRecordService;

  @ApiOperation(value = "机台故障记录提报")
  @PostMapping("save")
  public CommonResult<List<FaultRecord>> save(@RequestBody List<FaultRecord> faultRecords) throws Exception {

    return faultRecordService.save(faultRecords).get();
  }

}
