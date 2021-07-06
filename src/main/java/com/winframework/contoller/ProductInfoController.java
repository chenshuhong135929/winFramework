package com.winframework.contoller;

import com.winframework.common.CommonResult;
import com.winframework.entity.MachineInfo;
import com.winframework.entity.ProductInfo;
import com.winframework.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 10:27
 */
@RestController
@RequestMapping("productInfo")
@Api(value = "ProductInfoController")
public class ProductInfoController {

  @Autowired
  ProductInfoService productInfoService;


  @ApiOperation(value = "品名下拉列表")
  @PostMapping("selectProductInfo")
  public CommonResult<List<ProductInfo>> selectProductInfo(String  proName,String factory ) throws Exception {

    return productInfoService.selectProductInfo(proName,factory).get();
  }

}
