package com.spud.nimbus.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/1/31
 */
@Data
@ToString
public class UserSimpleInfoVO {

  @Schema(description = "用户昵称" ,requiredMode = Schema.RequiredMode.REQUIRED)
  private String nickName;

  @Schema(description = "用户头像" ,requiredMode = Schema.RequiredMode.REQUIRED)
  private String pic;
}
