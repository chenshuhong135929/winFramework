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
 * @Date 2020-11-25 9:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "故障记录信息",description = "故障记录信息表")
public class FaultRecord {
  @ApiModelProperty(value = "产品主键")
  @TableId(type = IdType.AUTO)
  private Long id;
  @ApiModelProperty(value = "生产记录Id")
  private long proReID;
  @ApiModelProperty(value = "机台编号")
  private String machineCode;
  @ApiModelProperty(value = "故障编号")
  private String faultCode;
  @ApiModelProperty(value = "记录时间")
  private Timestamp recordTime;
  @ApiModelProperty(value = "完成时间")
  private Timestamp finishTime;
}
