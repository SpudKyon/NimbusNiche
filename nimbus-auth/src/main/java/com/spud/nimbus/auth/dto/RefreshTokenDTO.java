package com.spud.nimbus.auth.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/17
 */
@Data
@ToString
public class RefreshTokenDTO extends BaseDTO {

	/**
	 * refreshToken
	 */
	@NotBlank(message = "refreshToken不能为空")
	@Schema(description = "refreshToken", requiredMode = Schema.RequiredMode.REQUIRED)
	private String refreshToken;

}