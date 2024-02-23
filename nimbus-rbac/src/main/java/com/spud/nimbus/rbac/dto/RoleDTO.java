package com.spud.nimbus.rbac.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/22
 */
@Data
@ToString
public class RoleDTO extends BaseDTO {
  private static final long serialVersionUID = 1L;

  @Schema(description = "角色id")
  private Long roleId;

  @Schema(description = "角色名称")
  private String roleName;

  @Schema(description = "备注")
  private String remark;

  @Schema(description = "菜单id列表")
  private List<Long> menuIds;

  @Schema(description = "菜单资源id列表")
  private List<Long> menuPermissionIds;
}
