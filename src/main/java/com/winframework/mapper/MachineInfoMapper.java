package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.MachineInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MachineInfoMapper extends BaseMapper<MachineInfo> {

  List<MachineInfo> selectMachineInfo();

  void updateStatus(@Param("machineCode") String machineCode, @Param("status") String status);

}
