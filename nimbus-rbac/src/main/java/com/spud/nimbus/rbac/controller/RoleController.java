package com.spud.nimbus.rbac.controller;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.rbac.dto.RoleDTO;
import com.spud.nimbus.rbac.model.Role;
import com.spud.nimbus.rbac.service.RoleService;
import com.spud.nimbus.rbac.vo.RoleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/23
 */
@RestController
@RequestMapping("v1/role")
@Tag(name = "角色")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @GetMapping("/page")
  @Operation(summary = "分页获取角色列表", description = "分页获取角色列表")
  public Result<PageVO<RoleVO>> page(@Valid PageDTO pageDTO) {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    PageVO<RoleVO> rolePage = roleService.page(pageDTO, userInfoInTokenBO.getSysType(), userInfoInTokenBO.getTenantId());
    return Result.success(rolePage);
  }

  @GetMapping("/list")
  @Operation(summary = "获取角色列表", description = "分页获取角色列表")
  public Result<List<RoleVO>> list() {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    return Result.success(roleService.list(userInfoInTokenBO.getSysType(), userInfoInTokenBO.getTenantId()));
  }

  @GetMapping
  @Operation(summary = "获取角色", description = "根据roleId获取角色")
  public Result<RoleVO> getByRoleId(@RequestParam Long roleId) {
    return Result.success(roleService.getByRoleId(roleId));
  }

  @PostMapping
  @Operation(summary = "保存角色", description = "保存角色")
  public Result<Void> save(@Valid @RequestBody RoleDTO roleDTO) {
    Role role = BeanUtil.map(roleDTO, Role.class);
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    role.setBizType(userInfoInTokenBO.getSysType());
    role.setRoleId(null);
    role.setCreateUserId(userInfoInTokenBO.getUserId());
    role.setTenantId(userInfoInTokenBO.getTenantId());
    roleService.save(role, roleDTO.getMenuIds(), roleDTO.getMenuPermissionIds());
    return Result.success(null);
  }

  @PutMapping
  @Operation(summary = "更新角色", description = "更新角色")
  public Result<Void> update(@Valid @RequestBody RoleDTO roleDTO) {

    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();


    RoleVO dbRole = roleService.getByRoleId(roleDTO.getRoleId());

    if (!Objects.equals(dbRole.getBizType(), userInfoInTokenBO.getSysType()) || !Objects.equals(dbRole.getTenantId(), userInfoInTokenBO.getTenantId())) {
      return Result.fail(ResultCode.UNAUTHORIZED, null);
    }
    Role role = BeanUtil.map(roleDTO, Role.class);
    role.setBizType(userInfoInTokenBO.getSysType());

    roleService.update(role, roleDTO.getMenuIds(), roleDTO.getMenuPermissionIds());
    return Result.success(null);
  }

  @DeleteMapping
  @Operation(summary = "删除角色", description = "根据角色id删除角色")
  public Result<Void> delete(@RequestParam Long roleId) {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();

    RoleVO dbRole = roleService.getByRoleId(roleId);

    if (!Objects.equals(dbRole.getBizType(), userInfoInTokenBO.getSysType()) || !Objects.equals(dbRole.getTenantId(), userInfoInTokenBO.getTenantId())) {
      return Result.fail(ResultCode.UNAUTHORIZED, null);
    }
    roleService.deleteById(roleId, userInfoInTokenBO.getSysType());
    return Result.success(null);
  }
}
