package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.ProductionRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductionRecordMapper  extends BaseMapper<ProductionRecord> {

  void addProductionRecord(ProductionRecord productionRecord);

  List<ProductionRecord>selectProductionRecord();

  void updateProductionRecord(ProductionRecord productionRecord);

  void updateProductionRecordIsError(@Param("isError") Boolean isError ,@Param("id")   long id);

  long   countProductionRecord(@Param("machineCode")  String machineCode );

  long selectByMachineCode(@Param("machineCode")  String machineCode );
}
