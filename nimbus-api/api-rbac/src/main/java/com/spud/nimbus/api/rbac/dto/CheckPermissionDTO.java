package com.spud.nimbus.api.rbac.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class CheckPermissionDTO {

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

	/**
	 * uri
	 */
	@NotBlank(message = "uri not blank")
	private String uri;

	/**
	 * 是否是管理员
	 */
	@NotNull(message = "isAdmin not null")
	private Integer isAdmin;

	/**
	 * 请求方法 1.GET 2.POST 3.PUT 4.DELETE
	 */
	private Integer method;

}
