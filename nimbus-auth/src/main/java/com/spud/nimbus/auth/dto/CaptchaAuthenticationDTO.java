package com.spud.nimbus.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/17
 */
@Data
@ToString
public class CaptchaAuthenticationDTO extends AuthenticationDTO {

  @Schema(description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED)
  private String captchaVerification;
}