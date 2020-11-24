package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.MachineUseStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MachineUseStatusMapper extends BaseMapper<MachineUseStatus> {

  void updateEndTime(@Param("machineCode") String machineCode);

  void addMachineUseStatus(MachineUseStatus machineUseStatus);


}
