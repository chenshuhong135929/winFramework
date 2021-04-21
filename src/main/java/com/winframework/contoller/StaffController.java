package com.winframework.contoller;

import com.winframework.common.CommonResult;
import com.winframework.entity.ProductInfo;
import com.winframework.entity.Staff;
import com.winframework.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 11:31
 */
@RestController
@RequestMapping("staff")
@Api(value = "StaffController员工接口")
public class StaffController {

  @Autowired
  StaffService staffService;

  @ApiOperation(value = "调机，生产  人员下拉列表")
  @PostMapping("selectStaff")
  public CommonResult<List<Staff>> selectStaff(String factory ) throws Exception {

    return staffService.selectStaff(factory).get();
  }
}
