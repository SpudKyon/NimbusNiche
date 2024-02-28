package com.spud.nimbus.api.rbac.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class ClearUserPermissionsCacheDTO {

	/**
	 * 用户id
	 */
	@NotNull(message = "userId not null")
	private Long userId;

	/**
	 * 系统类型
	 */
	@NotNull(message = "sysType not null")
	private Integer sysType;

}
