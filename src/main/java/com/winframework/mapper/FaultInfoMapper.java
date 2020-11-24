package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.FaultInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaultInfoMapper  extends BaseMapper<FaultInfo> {

  List<FaultInfo> selectFaultInfo();
}
