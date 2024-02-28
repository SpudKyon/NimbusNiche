package com.spud.nimbus.platform.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
public class ChangeAccountDTO extends BaseDTO {

	@NotNull(message = "userId not null")
	@Schema(description = "用户id")
	private Long userId;

	@NotBlank(message = "username not blank")
	@Schema(description = "用户名")
	private String username;

	@NotBlank(message = "password not blank")
	@Schema(description = "密码")
	private String password;

	@NotNull(message = "status not null")
	@Schema(description = "状态 1启用 0禁用")
	private Integer status;

}
