package com.spud.nimbus.rbac.controller;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.common.util.BooleanUtil;
import com.spud.nimbus.rbac.dto.MenuPermissionDTO;
import com.spud.nimbus.rbac.model.MenuPermission;
import com.spud.nimbus.rbac.service.MenuPermissionService;
import com.spud.nimbus.rbac.vo.MenuPermissionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/23
 */
@RequestMapping(value = "v1/menu_permission")
@RestController
@Tag(name = "权限接口")
public class MenuPermissionController {

  @Autowired
  private MenuPermissionService menuPermissionService;


  @GetMapping("/list_by_menu")
  @Operation(summary = "获取菜单资源列表", description = "分页获取菜单资源列表")
  public Result<List<MenuPermissionVO>> listByMenuId(Long menuId) {
    List<MenuPermissionVO> menuPermissionVOList = menuPermissionService.listByMenuId(menuId);
    return Result.success(menuPermissionVOList);
  }

  @GetMapping
  @Operation(summary = "获取菜单资源", description = "根据menuPermissionId获取菜单资源")
  public Result<MenuPermissionVO> getByMenuPermissionId(@RequestParam Long menuPermissionId) {
    return Result.success(menuPermissionService.getByMenuPermissionId(menuPermissionId));
  }

  @PostMapping
  @Operation(summary = "保存菜单资源", description = "保存菜单资源")
  public Result<Void> save(@Valid @RequestBody MenuPermissionDTO menuPermissionDTO) {
    MenuPermission menuPermission = BeanUtil.map(menuPermissionDTO, MenuPermission.class);
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    menuPermission.setMenuPermissionId(null);
    menuPermission.setBizType(userInfoInTokenBO.getSysType());
    boolean saved = menuPermissionService.save(menuPermission);
    return saved ? Result.success(null) : Result.showFailMsg("权限编码已存在，请勿重复添加");
  }

  @PutMapping
  @Operation(summary = "更新菜单资源", description = "更新菜单资源")
  public Result<Void> update(@Valid @RequestBody MenuPermissionDTO menuPermissionDTO) {
    MenuPermission menuPermission = BeanUtil.map(menuPermissionDTO, MenuPermission.class);
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    menuPermission.setBizType(userInfoInTokenBO.getSysType());
    return menuPermissionService.update(menuPermission);
  }

  @DeleteMapping
  @Operation(summary = "删除菜单资源", description = "根据菜单资源id删除菜单资源")
  public Result<Void> delete(@RequestParam Long menuPermissionId) {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    menuPermissionService.deleteById(menuPermissionId, userInfoInTokenBO.getSysType());
    return Result.success(null);
  }

  @GetMapping(value = "/list")
  @Operation(summary = "获取当前用户拥有的权限", description = "当前用户所拥有的所有权限，精确到按钮，实际上element admin里面的roles就可以理解成权限")
  public Result<List<String>> permissions() {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    return Result.success(menuPermissionService.listUserPermissions(userInfoInTokenBO.getUserId(),
            userInfoInTokenBO.getSysType(), BooleanUtil.isTrue(userInfoInTokenBO.getIsAdmin())));
  }

  @GetMapping(value = "/page")
  @Operation(summary = "获取当前用户拥有的权限", description = "当前用户所拥有的所有权限，精确到按钮，实际上element admin里面的roles就可以理解成权限")
  public Result<PageVO<MenuPermissionVO>> pagePermissions(PageDTO pageDTO) {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    PageVO<MenuPermissionVO> permissionPage = menuPermissionService.page(pageDTO, userInfoInTokenBO.getSysType());
    return Result.success(permissionPage);
  }

}
