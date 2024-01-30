package com.spud.nimbus.api.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * token信息，该信息用户返回给前端，前端请求携带accessToken进行用户校验
 *
 * @author spud
 * @date 2024/1/28
 */
public class TokenInfoVO {

  @Schema(description = "accessToken")
  private String accessToken;

  @Schema(description = "refreshToken")
  private String refreshToken;

  @Schema(description = "在多少秒后过期")
  private Integer expiresIn;
}
