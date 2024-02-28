package com.spud.nimbus.user.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/1/31
 */
@Data
@ToString
public class UserRegisterDTO extends BaseDTO {

	@NotBlank
	@Schema(description = "密码")
	private String password;

	@Schema(description = "头像")
	private String img;

	@Schema(description = "昵称")
	private String nickName;

	@NotBlank
	@Schema(description = "用户名")
	private String userName;

	@Schema(description = "当账户未绑定时，临时的uid")
	private String tempUid;

	@Schema(description = "用户id")
	private Long userId;

}
