package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.MachineInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MachineInfoMapper extends BaseMapper<MachineInfo> {

  List<MachineInfo> selectMachineInfo(@Param("factory") String factory );

  void updateStatus(@Param("machineCode") String machineCode,@Param("proName") String  proName,  @Param("status") String status);


}
