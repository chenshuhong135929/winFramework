package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.FaultRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FaultRecordMapper  extends BaseMapper<FaultRecord> {

      void  addFaultRecord(FaultRecord faultRecord);


      void updateFaultRecord(@Param("proReID") Long  proReID);
}
