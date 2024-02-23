package com.spud.nimbus.rbac.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/22
 */
@Data
@ToString
public class MenuPermissionSimpleVO extends BaseVO {

  private static final long serialVersionUID = 1L;

  @Schema(description = "菜单资源用户id")
  private Long menuPermissionId;

  @Schema(description = "资源关联菜单")
  private Long menuId;

  @Schema(description = "资源名称")
  private String name;
}
