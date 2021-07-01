package com.winframework.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther ChenShuHong
 * @Date 2020-11-25 9:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "故障信息")
public class FaultVo {
  @ApiModelProperty(value = "生产记录Id")
  private Long proReID;
  @ApiModelProperty(value = "机台号")
  private String machineCode;
  @ApiModelProperty(value = "品名")
  private String proName;
  @ApiModelProperty(value = "MusId")
  private Long musId;
  @ApiModelProperty(value = "故障编号")
  private List<String> faultCodes;
}
