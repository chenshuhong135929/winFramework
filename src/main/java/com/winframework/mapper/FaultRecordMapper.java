package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.FaultRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaultRecordMapper  extends BaseMapper<FaultRecord> {

      void  addFaultRecord(FaultRecord faultRecord);

}
