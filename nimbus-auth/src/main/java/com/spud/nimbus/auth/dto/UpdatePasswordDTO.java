package com.spud.nimbus.auth.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/17
 */
@Data
@ToString
public class UpdatePasswordDTO extends BaseDTO {

	@NotBlank(message = "oldPassword NotBlank")
	@Schema(description = "旧密码", requiredMode = Schema.RequiredMode.REQUIRED)
	private String oldPassword;

	@NotNull(message = "newPassword NotNull")
	@Schema(description = "新密码", requiredMode = Schema.RequiredMode.REQUIRED)
	private String newPassword;

}