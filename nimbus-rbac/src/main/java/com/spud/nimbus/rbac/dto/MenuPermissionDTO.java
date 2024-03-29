package com.spud.nimbus.rbac.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/22
 */
@Data
@ToString
public class MenuPermissionDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "菜单资源用户id")
	private Long menuPermissionId;

	@NotNull(message = "menuId NotNull")
	@Schema(description = "资源关联菜单")
	private Long menuId;

	@NotBlank(message = "permission NotBlank")
	@Schema(description = "权限对应的编码")
	private String permission;

	@Schema(description = "资源名称")
	private String name;

	@Schema(description = "资源对应服务器路径")
	@NotBlank(message = "uri NotBlank")
	private String uri;

	@NotNull(message = "method NotNull")
	@Schema(description = "请求方法 1.GET 2.POST 3.PUT 4.DELETE")
	private Integer method;

}
