package com.winframework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ResultMap;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 10:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "品名信息",description = "品名信息表")
public class ProductInfo {
  @ApiModelProperty(value = "产品主键")
  @TableId(type = IdType.AUTO)
  private Long id;
  @ApiModelProperty(value = "品名")
  private String proName;
  @ApiModelProperty(value = "用料")
  private String material;
  @ApiModelProperty(value = "模号")
  private String mouldCode;
  @ApiModelProperty(value = "穴数")
  private Long stdCavityQty;
  @ApiModelProperty(value = "周期")
  private double cycleTime;
  @ApiModelProperty(value = "流道类型")
  private String flowType;
  @ApiModelProperty(value = "工厂")
  private String factory;

}
