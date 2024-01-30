package com.spud.nimbus.api.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author spud
 * @date 2024/1/28
 */
public class AuthAccountVO {
  /**
   * 全平台用户唯一id
   */
  private Long uid;

  @Schema(description = "用户id")
  private Long userId;

  @Schema(description = "用户名")
  private String username;

  @Schema(description = "状态 1:启用 0:禁用 -1:删除")
  private Integer status;

  @Schema(description = "创建ip")
  private String createIp;
}
