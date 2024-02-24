package com.spud.nimbus.multishop.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/24
 */
@Data
@ToString
@Schema(description = "用户名和密码参数")
public class UsernameAndPasswordDTO extends BaseDTO {

  @NotBlank(message = "用户名不能为空")
  @Size(max = 30)
  @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
  private String username;

  @NotBlank(message = "密码不能为空")
  @Size(max = 64)
  @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
  private String password;

  @Schema(description = "店铺id")
  private Long shopId;
}
