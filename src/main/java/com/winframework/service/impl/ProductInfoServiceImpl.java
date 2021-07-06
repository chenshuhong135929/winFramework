package com.winframework.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winframework.common.CommonResult;
import com.winframework.entity.ProductInfo;
import com.winframework.mapper.ProductInfoMapper;
import com.winframework.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 10:35
 */
@Service
@Slf4j
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

  @Autowired
  ProductInfoMapper productInfoMapper;


  @Override
  public CompletableFuture<CommonResult<List<ProductInfo>>> selectProductInfo(String proName,String factory ) {
    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<ProductInfo>>> future = CompletableFuture.supplyAsync(() -> {
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("获取品名成功。");
      result.setData( productInfoMapper.selectProductInfo(proName,factory));
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("获取品名下拉设备出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }
}
