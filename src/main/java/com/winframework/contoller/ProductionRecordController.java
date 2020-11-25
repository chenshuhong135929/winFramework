package com.winframework.contoller;

import com.winframework.common.CommonResult;
import com.winframework.entity.ProductionRecord;
import com.winframework.entity.Staff;
import com.winframework.service.ProductionRecordService;
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
 * @Date 2020-11-24 13:53
 */
@RestController
@RequestMapping("productionRecord")
@Api(value = "ProductionRecordController生产记录接口")
public class ProductionRecordController  {

  @Autowired
  ProductionRecordService productionRecordService;


  @ApiOperation(value = "开始调机")
  @PostMapping("startProductionRecord")
  public CommonResult startProductionRecord(@RequestBody  ProductionRecord productionRecord) throws Exception {

    return productionRecordService.startProductionRecord(productionRecord).get();
  }

  @ApiOperation(value = "显示调机列表")
  @PostMapping("selectProductionRecord")
  public CommonResult<List<ProductionRecord>> selectProductionRecord() throws Exception {

    return productionRecordService.selectProductionRecord().get();
  }

  @ApiOperation(value = "更新调机列表")
  @PostMapping("updateProductionRecords")
  public CommonResult updateProductionRecords(@RequestBody  List<ProductionRecord> productionRecords) throws Exception {

    return productionRecordService.updateProductionRecords(productionRecords).get();
  }


}
