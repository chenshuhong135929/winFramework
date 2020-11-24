package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {

  List<ProductInfo> selectProductInfo(@Param("proName")  String  proName);

}
