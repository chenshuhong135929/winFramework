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
 * @Date 2020-11-24 17:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "故障选项信息",description = "故障选项信息表")
public class FaultInfo {
  @ApiModelProperty(value = "产品主键")
  @TableId(type = IdType.AUTO)
  private Long id;
  @ApiModelProperty(value = "故障编号")
  private String faultCode;
  @ApiModelProperty(value = "类型")
  private String FaultType;
  @ApiModelProperty(value = "名称")
  private String FaultName;
}
