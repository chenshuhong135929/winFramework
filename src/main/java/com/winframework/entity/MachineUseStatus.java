package com.winframework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 14:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "机台使用状态",description = "机台使用状态信息表")
public class MachineUseStatus {
  @ApiModelProperty(value = "产品主键")
  @TableId(type = IdType.AUTO)
  private Long id;
  @ApiModelProperty(value = "机台号")
  private String machineCode;
  @ApiModelProperty(value = "品名")
  private String proName;
  @ApiModelProperty(value = "生产记录Id")
  private Long proRecordID;
  @ApiModelProperty(value = "机台状态")
  private long status;
  @ApiModelProperty(value = "生产日期")
  private Timestamp beginTime;
  @ApiModelProperty(value = "结束日期")
  private Timestamp endTime;
}
