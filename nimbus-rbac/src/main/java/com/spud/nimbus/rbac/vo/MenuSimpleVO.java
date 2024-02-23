package com.spud.nimbus.rbac.vo;

import com.spud.nimbus.common.vo.BaseVO;
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
public class MenuSimpleVO extends BaseVO {

  private static final long serialVersionUID = 1L;

  @Schema(description = "菜单id")
  private Long menuId;

  @Schema(description = "父菜单ID，一级菜单为0")
  private Long parentId;

  @Schema(description = "设置该路由在侧边栏和面包屑中展示的名字")
  private String title;

  @Schema(description = "菜单权限列表")
  private List<MenuPermissionSimpleVO> menuPermissions;
}
