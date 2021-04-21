package com.winframework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winframework.entity.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 11:26
 */
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {

  List<Staff>  selectStaff( @Param("factory") String factory);
}
