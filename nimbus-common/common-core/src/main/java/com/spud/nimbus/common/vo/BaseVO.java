package com.spud.nimbus.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * @author spud
 * @date 2024/1/28
 */
public class BaseVO implements Serializable {

  /**
   * 创建时间
   */
  @Schema(description = "创建时间")
  protected Date createTime;

  /**
   * 更新时间
   */
  @Schema(description = "更新时间")
  protected Date updateTime;
}