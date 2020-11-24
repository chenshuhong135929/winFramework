package com.winframework.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 9:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "机台信息",description = "机台信息表")
public class MachineInfo {
  @ApiModelProperty(value = "机台号")
  private String machineCode;
  @ApiModelProperty(value = "设备名称")
  private String machineName;
  @ApiModelProperty(value = "设备状态")
  private String isAuto;

}
