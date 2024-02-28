package com.spud.nimbus.platform.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
public class SysUserDTO extends BaseDTO {

	@Schema(description = "平台用户id")
	private Long sysUserId;

	@NotBlank(message = "昵称不能为空")
	@Schema(description = "昵称")
	private String nickName;

	@NotBlank(message = "头像不能为空")
	@Schema(description = "头像")
	private String avatar;

	@Schema(description = "员工编号")
	private String code;

	@Schema(description = "联系方式")
	private String phoneNum;

	@Schema(description = "角色id列表")
	private List<Long> roleIds;

}
