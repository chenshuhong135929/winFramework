package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.VFault;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VFaultMapper extends BaseMapper<VFault> {

  List<VFault> selectByProRecordID(@Param("proRecordID") String proRecordID);
}
