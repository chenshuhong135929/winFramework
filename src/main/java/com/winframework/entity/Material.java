package com.winframework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther ChenShuHong
 * @Date 2020-12-23 15:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用料",description = "用料信息表")
public class Material {
  @ApiModelProperty(value = "产品主键")
  @TableId(type = IdType.AUTO)
  private Long id;
  @ApiModelProperty(value = "品名ID")
  private Long proId;
  @ApiModelProperty(value = "用料名")
  private String materialName;
}
