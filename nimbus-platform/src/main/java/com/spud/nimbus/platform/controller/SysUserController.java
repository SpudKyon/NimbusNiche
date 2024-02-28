package com.spud.nimbus.platform.controller;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.platform.dto.SysUserDTO;
import com.spud.nimbus.platform.model.SysUser;
import com.spud.nimbus.platform.service.SysUserService;
import com.spud.nimbus.platform.vo.SysUserSimpleVO;
import com.spud.nimbus.platform.vo.SysUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author spud
 * @date 2024/2/25
 */
@RequestMapping(value = "/sys_user")
@RestController
@Tag(name = "平台用户信息")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	@GetMapping("/info")
	@Operation(summary = "登陆平台用户信息", description = "获取当前登陆平台用户的用户信息")
	public Result<SysUserSimpleVO> info() {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		SysUserSimpleVO sysUserSimple = sysUserService.getSimpleByUserId(userInfoInTokenBO.getUserId());
		sysUserSimple.setIsAdmin(userInfoInTokenBO.getIsAdmin());
		return Result.success(sysUserSimple);
	}

	@GetMapping("/page")
	@Operation(summary = "平台用户列表", description = "获取平台用户列表")
	public Result<PageVO<SysUserVO>> page(@Valid PageDTO pageDTO, String nickName) {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		PageVO<SysUserVO> sysUserPage = sysUserService.pageByShopId(pageDTO, userInfoInTokenBO.getTenantId(), nickName);
		return Result.success(sysUserPage);
	}

	@GetMapping
	@Operation(summary = "获取平台用户信息", description = "根据用户id获取平台用户信息")
	public Result<SysUserVO> detail(@RequestParam Long sysUserId) {
		return Result.success(sysUserService.getByUserId(sysUserId));
	}

	@PostMapping
	@Operation(summary = "保存平台用户信息", description = "保存平台用户信息")
	public Result<Void> save(@Valid @RequestBody SysUserDTO sysUserDTO) {
		SysUser sysUser = BeanUtil.map(sysUserDTO, SysUser.class);
		sysUser.setSysUserId(null);
		sysUser.setHasAccount(0);
		sysUserService.save(sysUser, sysUserDTO.getRoleIds());
		return Result.success(null);
	}

	@PutMapping
	@Operation(summary = "更新平台用户信息", description = "更新平台用户信息")
	public Result<Void> update(@Valid @RequestBody SysUserDTO sysUserDTO) {
		SysUser sysUser = BeanUtil.map(sysUserDTO, SysUser.class);
		sysUserService.update(sysUser, sysUserDTO.getRoleIds());
		return Result.success(null);
	}

	@DeleteMapping
	@Operation(summary = "删除平台用户信息", description = "根据平台用户id删除平台用户信息")
	public Result<Void> delete(@RequestParam Long sysUserId) {
		sysUserService.deleteById(sysUserId);
		return Result.success(null);
	}

}
