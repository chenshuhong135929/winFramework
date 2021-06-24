package com.winframework.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther ChenShuHong
 * @Date 2021-06-23 14:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "故障视图",description = "故障视图")
public class VFault {
  @ApiModelProperty(value = "id")
  private String id;
  @ApiModelProperty(value = "生产记录Id")
  private String proRecordID;
  @ApiModelProperty(value = "开始时间")
  private Date beginTime;
  @ApiModelProperty(value = "开始时间")
  private Date endTime;
  @ApiModelProperty(value = "停机时常")
  private String mi;
  @ApiModelProperty(value = "故障")
  private String faultName;
  @ApiModelProperty(value = "是否错误")
  private Boolean isError;
}
