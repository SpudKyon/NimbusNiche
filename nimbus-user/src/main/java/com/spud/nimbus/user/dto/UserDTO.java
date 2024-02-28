package com.spud.nimbus.user.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/1/31
 */
@Data
@ToString
public class UserDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "ID")
	private Long userId;

	@Schema(description = "用户昵称")
	private String nickName;

	@Schema(description = "头像图片路径")
	private String pic;

	@Schema(description = "状态 1 正常 0 无效")
	private Integer status;

}
