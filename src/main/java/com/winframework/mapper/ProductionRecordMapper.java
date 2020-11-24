package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.ProductionRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductionRecordMapper  extends BaseMapper<ProductionRecord> {

  void addProductionRecord(ProductionRecord productionRecord);

  List<ProductionRecord>selectProductionRecord();

  void updateProductionRecord(ProductionRecord productionRecord);
}
