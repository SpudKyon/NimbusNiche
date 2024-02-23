package com.spud.nimbus.rbac.controller;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.common.util.BooleanUtil;
import com.spud.nimbus.rbac.dto.MenuDTO;
import com.spud.nimbus.rbac.model.Menu;
import com.spud.nimbus.rbac.service.MenuService;
import com.spud.nimbus.rbac.vo.MenuSimpleVO;
import com.spud.nimbus.rbac.vo.MenuVO;
import com.spud.nimbus.rbac.vo.RouteMetaVO;
import com.spud.nimbus.rbac.vo.RouteVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/23
 */
@RequestMapping(value = "v1/menu")
@RestController
@Tag(name = "菜单接口")
public class MenuController {

  @Autowired
  private MenuService menuService;

  @GetMapping(value = "/route")
  @Operation(summary = "路由菜单", description = "获取当前登陆用户可用的路由菜单列表")
  public Result<List<RouteVO>> route(Integer sysType) {
    sysType = Objects.isNull(sysType) ? AuthUserContext.get().getSysType() : sysType;
    List<Menu> menus = menuService.listBySysType(sysType);

    List<RouteVO> routes = new ArrayList<>(menus.size());

    for (Menu menu : menus) {
      RouteVO route = new RouteVO();
      route.setAlwaysShow(BooleanUtil.isTrue(menu.getAlwaysShow()));
      route.setComponent(menu.getComponent());
      route.setHidden(BooleanUtil.isTrue(menu.getHidden()));
      route.setName(menu.getName());
      route.setPath(menu.getPath());
      route.setRedirect(menu.getRedirect());
      route.setId(menu.getMenuId());
      route.setParentId(menu.getParentId());
      route.setSeq(menu.getSeq());

      RouteMetaVO meta = new RouteMetaVO();
      meta.setActiveMenu(menu.getActiveMenu());
      meta.setAffix(BooleanUtil.isTrue(menu.getAffix()));
      meta.setBreadcrumb(BooleanUtil.isTrue(menu.getBreadcrumb()));
      meta.setIcon(menu.getIcon());
      meta.setNoCache(BooleanUtil.isTrue(menu.getNoCache()));
      meta.setTitle(menu.getTitle());
      // 对于前端来说角色就是权限
      meta.setRoles(Collections.singletonList(menu.getPermission()));

      route.setMeta(meta);
      routes.add(route);
    }
    return Result.success(routes);
  }

  @GetMapping
  @Operation(summary = "获取菜单管理", description = "根据menuId获取菜单管理")
  public Result<MenuVO> getByMenuId(@RequestParam Long menuId) {
    return Result.success(menuService.getByMenuId(menuId));
  }

  @PostMapping
  @Operation(summary = "保存菜单管理", description = "保存菜单管理")
  public Result<Void> save(@Valid @RequestBody MenuDTO menuDTO) {
    Menu menu = checkAndGenerate(menuDTO);
    menu.setMenuId(null);
    menuService.save(menu);
    return Result.success(null);
  }

  private Menu checkAndGenerate(@RequestBody @Valid MenuDTO menuDTO) {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    if (!Objects.equals(userInfoInTokenBO.getTenantId(), 0L)) {
      throw new NimbusException("无权限操作！");
    }
    Menu menu = BeanUtil.map(menuDTO, Menu.class);
    menu.setBizType(menuDTO.getSysType());
    if (Objects.isNull(menuDTO.getSysType())) {
      menu.setBizType(AuthUserContext.get().getSysType());
    }
    return menu;
  }

  @PutMapping
  @Operation(summary = "更新菜单管理", description = "更新菜单管理")
  public Result<Void> update(@Valid @RequestBody MenuDTO menuDTO) {
    Menu menu = checkAndGenerate(menuDTO);
    menuService.update(menu);
    return Result.success(null);
  }

  @DeleteMapping
  @Operation(summary = "删除菜单管理", description = "根据菜单管理id删除菜单管理")
  public Result<Void> delete(@RequestParam("menuId") Long menuId, @RequestParam("sysType") Integer sysType) {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    if (!Objects.equals(userInfoInTokenBO.getTenantId(), 0L)) {
      throw new NimbusException("无权限操作！");
    }
    sysType = Objects.isNull(sysType) ? userInfoInTokenBO.getSysType() : sysType;
    menuService.deleteById(menuId, sysType);
    return Result.success(null);
  }

  @GetMapping(value = "/list_with_permissions")
  @Operation(summary = "菜单列表和按钮列表", description = "根据系统类型获取该系统的菜单列表 + 菜单下的权限列表")
  public Result<List<MenuSimpleVO>> listWithPermissions() {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    List<MenuSimpleVO> menuList = menuService.listWithPermissions(userInfoInTokenBO.getSysType());
    return Result.success(menuList);
  }

  @GetMapping(value = "/list_menu_ids")
  @Operation(summary = "获取当前用户可见的菜单ids", description = "获取当前用户可见的菜单id")
  public Result<List<Long>> listMenuIds() {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    List<Long> menuList = menuService.listMenuIds(userInfoInTokenBO.getUserId());
    return Result.success(menuList);
  }
}