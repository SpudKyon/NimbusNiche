package com.spud.nimbus.api.user.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 省市区地区信息VO
 * @author spud
 * @date 2024/1/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class AreaVO extends BaseVO {
  private static final long serialVersionUID = 1L;

  private Long areaId;

  @Schema(description = "地址")
  private String areaName;

  @Schema(description = "上级地址")
  private Long parentId;

  @Schema(description = "等级（从1开始）")
  private Integer level;

  private Integer check;

  /**
   * 下级地址集合
   */
  private List<AreaVO> areas;

  /**
   * 下级地址的areaId
   */
  private List<Long> areaIds;
}
