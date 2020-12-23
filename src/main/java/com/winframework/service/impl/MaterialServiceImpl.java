package com.winframework.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.winframework.common.CommonResult;
import com.winframework.entity.Material;
import com.winframework.mapper.MaterialMapper;
import com.winframework.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther ChenShuHong
 * @Date 2020-12-23 16:04
 */
@Service
@Slf4j
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {

  @Autowired
  MaterialMapper materialMapper;

  @Override
  public CompletableFuture<CommonResult<List<Material>>> selectMaterial(Long proId) {
    CommonResult result = new CommonResult();
    CompletableFuture<CommonResult<List<Material>>> future = CompletableFuture.supplyAsync(() -> {
      result.setCode(HttpStatus.HTTP_OK);
      result.setMessage("获取用料成功。");
      result.setData( materialMapper.selectMaterial(proId));
      return result;
    });
    future.exceptionally((e) -> {
      e.printStackTrace();
      result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
      log.error("获取用料下拉设备出错["+e.getMessage() +"]");
      result.setMessage("系统出错误，请联系管理员。");
      return result;
    });
    return future;
  }
}
