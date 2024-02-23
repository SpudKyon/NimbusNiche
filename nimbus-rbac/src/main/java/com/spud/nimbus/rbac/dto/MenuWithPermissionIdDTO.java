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
public class MenuWithPermissionIdDTO extends BaseDTO {

  @Schema(description = "菜单id")
  private Long menuId;

  @Schema(description = "菜单下的权限id列表")
  private List<Long> permissionIds;
}
