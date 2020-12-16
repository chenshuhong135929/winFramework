package com.winframework.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther ChenShuHong
 * @Date 2020-12-16 9:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户",description = "用户表")
public class User {
  @ApiModelProperty(value = "名称")
  private String name;
  @ApiModelProperty(value = "密码")
  private String password;
}
