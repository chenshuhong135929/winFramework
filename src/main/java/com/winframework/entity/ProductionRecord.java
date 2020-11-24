package com.winframework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-24 11:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "生产记录 ",description = "生产记录 表")
public class ProductionRecord {
  @ApiModelProperty(value = "产品主键")
  @TableId(type = IdType.AUTO)
  private Long id;
  @ApiModelProperty(value = "机台号")
  private String machineCode;
  @ApiModelProperty(value = "采集器MAC地址")
  private String collectMac;
  @ApiModelProperty(value = "品名")
  private String proName;
  @ApiModelProperty(value = "用料")
  private String material;
  @ApiModelProperty(value = "模号")
  private String mouldCode;
  @ApiModelProperty(value = "穴数")
  private Long stdCavityQty;
  @ApiModelProperty(value = "生产穴数")
  private Long useCavityQty;
  @ApiModelProperty(value = "周期")
  private Long cycleTime;
  @ApiModelProperty(value = "生产周期")
  private Long useCycleTime;
  @ApiModelProperty(value = "流道类型")
  private String flowType;
  @ApiModelProperty(value = "生产数量")
  private Long qty;
  @ApiModelProperty(value = "数量")
  private Long mQty;
  @ApiModelProperty(value = "生产日期")
  private Date proDate;
  @ApiModelProperty(value = "班别")
  private String classes;
  @ApiModelProperty(value = "调机作业员")
  private String debugPerson;
  @ApiModelProperty(value = "生产作业员")
  private String proPerson;
  @ApiModelProperty(value = "操作")
  private Long operating;
  @ApiModelProperty(value = "是否自动")
  private Boolean isAuto;
  @ApiModelProperty(value = "是否错误")
  private Boolean isError;
  @ApiModelProperty(value = "是否完成")
  private Boolean isFinish;
}
