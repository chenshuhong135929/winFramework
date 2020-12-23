package com.winframework.service;

import com.winframework.common.CommonResult;
import com.winframework.entity.Material;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MaterialService {

  CompletableFuture<CommonResult<List<Material>>> selectMaterial(Long  proId);
}
