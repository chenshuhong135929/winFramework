package com.winframework.contoller;

import com.winframework.common.CommonResult;
import com.winframework.entity.Material;
import com.winframework.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-12-23 16:02
 */
@RestController
@RequestMapping("material")
@Api(value = "MaterialController")
public class MaterialController {

  @Autowired
  MaterialService materialService;
  @ApiOperation(value = "用料下拉列表")
  @PostMapping("selectMaterial")
  public CommonResult<List<Material>> selectMaterial(Long proId) throws Exception {

    return materialService.selectMaterial(proId).get();
  }

}
