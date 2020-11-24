package com.winframework.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 11:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "员工信息",description = "员工信息表")
public class Staff {
  @ApiModelProperty(value = "名称")
  private String name;
  @ApiModelProperty(value = "关键字")
  private String jobNo;
}
