package com.spud.nimbus.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author spud
 * @date 2024/1/28
 */

@Data
@ToString
public class BaseDTO {

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
