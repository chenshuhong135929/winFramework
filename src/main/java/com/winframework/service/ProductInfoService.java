package com.winframework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.winframework.common.CommonResult;
import com.winframework.entity.ProductInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProductInfoService extends IService<ProductInfo> {

  /**
   *  品名下拉
   * @param proName
   * @return
   */
  CompletableFuture<CommonResult<List<ProductInfo>>> selectProductInfo(String  proName,String factory );
}
